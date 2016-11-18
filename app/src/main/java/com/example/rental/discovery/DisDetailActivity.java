package com.example.rental.discovery;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rental.R;
import com.example.rental.discovery.GridViewTest.PictureGridView;
import com.example.rental.service.BaseService;
import com.example.rental.service.Listener;
import com.example.rental.util.HttpRequest;
import com.loopj.android.http.RequestParams;

/**
 * Created by caolu on 2016/11/17.
 */

public class DisDetailActivity extends Activity {

    private ImageView photo;
    private TextView name,time,title,info;
    private ListView mListView;
    private PictureGridView mGridView;
    private DisAdapter mAdapter;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dis_detail);
        initView();
        downloadInfo();
    }

    private void downloadInfo() {
        RequestParams params = new RequestParams();
        try {
            BaseService.get(url, params, new Listener() {
                @Override
                public void onSuccess(Object object) {

                }

                @Override
                public void onFailure(String msg) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        mListView.setAdapter(mAdapter);
    }

    private void initView() {
        photo = (ImageView) findViewById(R.id.dis_detail_photo);
        name = (TextView) findViewById(R.id.dis_detail_name);
        time = (TextView) findViewById(R.id.dis_detail_time);
        title = (TextView) findViewById(R.id.dis_detail_title);
        info = (TextView) findViewById(R.id.dis_detail_info);
        mListView = (ListView) findViewById(R.id.dis_detail_pinglun);
        mGridView = (PictureGridView) findViewById(R.id.dis_detail_gridview);
        mAdapter = new DisAdapter(this);

    }
}
