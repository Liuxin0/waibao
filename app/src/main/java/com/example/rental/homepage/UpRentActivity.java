package com.example.rental.homepage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rental.R;
import com.example.rental.util.PicassoImageLoader;

import java.io.File;
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
        img.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uprent_cancle:
                finish();
                break;
            case R.id.uprent_doup:
                break;
            case R.id.uprent_img:
                selectImage();
                break;
        }
    }

    private void selectImage() {
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
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
                File file = new File(resultList.get(0).getPhotoPath());
                /**
                 * 后面三个参数  加载失败图片，长宽值。
                 */
                imageLoader.displayImage(UpRentActivity.this,resultList.get(0).getPhotoPath(),img,null,600,600);
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });

    }


}
