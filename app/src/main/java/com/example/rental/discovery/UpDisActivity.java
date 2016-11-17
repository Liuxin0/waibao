package com.example.rental.discovery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rental.R;
import com.example.rental.discovery.GridViewTest.PictureGridView;
import com.example.rental.homepage.UpRentActivity;
import com.example.rental.model.BaseModel;
import com.example.rental.service.BaseService;
import com.example.rental.service.Listener;
import com.example.rental.util.BaseUtil;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by caolu on 2016/11/14.
 */

public class UpDisActivity extends Activity {

    private EditText mTitle, mContent;
    private TextView mDoUp, mCancle;
    private PictureGridView mGridView;
    private static final int maxImage = 9;
    private static final String URL= BaseUtil.BASE_URL+"sendcircle";
    private UpGridViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.updis_activity);
        initView();
    }

    private void initView() {
        mTitle = (EditText) findViewById(R.id.updis_title);
        mContent = (EditText) findViewById(R.id.updis_content);
        mGridView = (PictureGridView) findViewById(R.id.updis_gridview);
        mDoUp = (TextView) findViewById(R.id.updis_up);
        mCancle = (TextView) findViewById(R.id.updis_cancle);
        mGridView.setNumColumns(3);
        mAdapter = new UpGridViewAdapter(this, 1, 3);
        mGridView.setAdapter(mAdapter);
        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mDoUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<PhotoInfo> list = mAdapter.getPicture();


                RequestParams params = new RequestParams();
                if (list!=null) {
                    for (int i = 0; i < list.size(); i++) {
                        File file = new File(list.get(i).getPhotoPath());
                        int j = i + 1;
                        try {
                            params.put("Picture" + j, file);
                            Log.i("picture", j + "");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    params.put("num", list.size() + "");
                }else{
                    params.put("num", 0 + "");
                }
                params.put("Title",mTitle.getText().toString());
                params.put("UserPhone", "15248073068");
                params.put("SecretKey", "abdefeacd7a345860276994e6bffc805");
                params.put("Information",mContent.getText().toString());
                try {
                    BaseService.post(URL, params, new Listener() {
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
                            //mDoUp.setOnClickListener(UpDisActivity.this);
                        }

                        @Override
                        public void onFailure(String msg) {
                            //mDoUp.setOnClickListener(UpDisActivity.this);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
