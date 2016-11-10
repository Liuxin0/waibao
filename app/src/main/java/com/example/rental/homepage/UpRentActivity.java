package com.example.rental.homepage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rental.R;
import com.example.rental.model.BaseModel;
import com.example.rental.model.UserModel;
import com.example.rental.service.BaseService;
import com.example.rental.service.Listener;
import com.example.rental.service.PhotoService;
import com.example.rental.util.BaseUtil;
import com.example.rental.util.PicassoImageLoader;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.BuildConfig;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by caolu on 2016/11/5.
 */

public class UpRentActivity extends Activity implements View.OnClickListener {

    private TextView cancle, doup;
    private GFImageView img;
    private EditText info;
    private String imgFilePath;
    private static final String TargetURL = "http://183.175.12.181:8000/sendhezu";
    private static final String Path = Environment.getExternalStorageDirectory().getPath() + "/temp.jpg";
    private File photoFile;
    private Spinner mSpinner;
    private int num = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.uprent_activity);

        initView();
    }

    private void initView() {
        img = (GFImageView) findViewById(R.id.uprent_img);
        cancle = (TextView) findViewById(R.id.uprent_cancle);
        doup = (TextView) findViewById(R.id.uprent_doup);
        info = (EditText) findViewById(R.id.uprent_info);
        mSpinner = (Spinner) findViewById(R.id.uprent_num);
        img.setOnClickListener(this);
        doup.setOnClickListener(this);
        cancle.setOnClickListener(this);
        initSpinner();
    }

    private void initSpinner() {
        List<String> data_list = new ArrayList<>();
        data_list.add("2人");
        data_list.add("3人");
        data_list.add("4人");
        data_list.add("5人及以上");
        ArrayAdapter<String> arr_adapter;
        arr_adapter = new ArrayAdapter<String>(this, R.layout.my_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        mSpinner.setAdapter(arr_adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSpinner.setSelection(i, true);
                num = i + 2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uprent_cancle:
                finish();
                break;
            case R.id.uprent_doup:
                Log.i("onclick", "yes");
                final String infomation = info.getText().toString();
                Log.i("info", infomation);

                if (imgFilePath != null) {
                    try {
                        RequestParams params = new RequestParams();
                        params.put("Picture", photoFile);
                        params.put("Information", infomation);
                        params.put("UserPhone", "15248073068");
                        params.put("SecretKey", "abdefeacd7a345860276994e6bffc805");
                        params.put("Number", num);
                        params.put("Address", "呼和浩特");
                        doup.setOnClickListener(null);
                        BaseService.post(TargetURL, params, new Listener() {
                            @Override
                            public void onSuccess(Object object) {
                                BaseModel model = (BaseModel) object;
                                if (model != null) {
                                    if (model.getState() == 1) {
                                        Toast.makeText(getApplicationContext(), model.getMsg(), Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), model.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), model.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                                doup.setOnClickListener(UpRentActivity.this);
                            }

                            @Override
                            public void onFailure(String msg) {
                                doup.setOnClickListener(UpRentActivity.this);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        doup.setOnClickListener(UpRentActivity.this);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "请选择图片", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.uprent_img:
                selectImage();
                break;
        }
    }


    private void selectImage() {
        //设置主题
        //ThemeConfig.CYAN
        final ThemeConfig theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        final PicassoImageLoader imageLoader = new PicassoImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageLoader, theme)
                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
        int REQUEST_CODE_GALLERY = 0;
        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                /**
                 * 准备上传图片  File file;
                 */
                imgFilePath = resultList.get(0).getPhotoPath();
                double size = new File(imgFilePath).length();
                boolean isZip = false;
                int yasuobi = 1;
                Log.i("前", size + "");
                if (size > 1500000) {
                    yasuobi = 8;
                    isZip = true;
                } else if (size > 1000000) {
                    yasuobi = 4;
                    isZip = true;
                }
                if (isZip) {
                    Bitmap b = BaseUtil.getImage(imgFilePath, yasuobi);
                    photoFile = BaseUtil.getBitmapFile(b, Path);
                } else {
                    photoFile = new File(imgFilePath);
                }
                Log.i("后", "" + photoFile.length());
                /**
                 * 后面三个参数  加载失败图片，长宽值。
                 */
                imageLoader.displayImage(UpRentActivity.this, resultList.get(0).getPhotoPath(), img, null, 600, 600);
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });

    }


}
