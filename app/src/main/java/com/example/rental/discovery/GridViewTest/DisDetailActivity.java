package com.example.rental.discovery.GridViewTest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rental.R;

/**
 * Created by caolu on 2016/11/17.
 */

public class DisDetailActivity extends Activity {

    private ImageView photo;
    private TextView name,time,title,into;
    private ListView mListView;
    private PictureGridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dis_detail);
    }
}
