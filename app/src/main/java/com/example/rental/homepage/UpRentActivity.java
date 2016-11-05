package com.example.rental.homepage;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rental.R;

/**
 * Created by caolu on 2016/11/5.
 */

public class UpRentActivity extends Activity implements View.OnClickListener{
    
    private TextView cancle,doup;
    private ImageView img;
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
        img = (ImageView) findViewById(R.id.uprent_img);
        cancle  = (TextView) findViewById(R.id.uprent_cancle);
        doup  = (TextView) findViewById(R.id.uprent_doup);
        info = (EditText) findViewById(R.id.uprent_info);
        img.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.uprent_cancle:
                finish();
                break;
            case R.id.uprent_doup:
                break;
            case R.id.uprent_img:
            break;
        }
    }
}
