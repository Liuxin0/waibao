package com.example.rental.discovery;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rental.R;
import com.example.rental.discovery.GridViewTest.PictureGridView;

/**
 * Created by caolu on 2016/11/14.
 */

public class UpDisActivity extends Activity {

    private EditText mTitle, mContent;
    private TextView mDoUp, mCancle;
    private PictureGridView mGridView;
    private static final int maxImage = 9;

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
        mGridView.setAdapter(new UpGridViewAdapter(this, 1, 3));
    }
}
