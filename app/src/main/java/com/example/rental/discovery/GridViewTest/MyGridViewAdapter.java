package com.example.rental.discovery.GridViewTest;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rental.R;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by caolu on 2016/11/5.
 */

public class MyGridViewAdapter extends BaseAdapter {

    private Context context;
    private int num;
    private int col;

    public MyGridViewAdapter(Context context, int num, int col) {
        this.context = context;
        this.num = num;
        this.col = col;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return num;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        int width = WindowSize.getWidth(context);// 获取屏幕宽度
        Log.i("tag", "width" + width);
        int height = 0;
        width = width / col;// 对当前的列数进行设置imgView的宽度
        if (num == 1) {
            width /= 3;
        }
        height = width;
        img.setLayoutParams(new AbsListView.LayoutParams(width, height));
        img.setImageResource(R.drawable.user_photo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return img;
    }
}
