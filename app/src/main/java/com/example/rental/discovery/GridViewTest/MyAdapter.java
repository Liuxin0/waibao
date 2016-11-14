package com.example.rental.discovery.GridViewTest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.rental.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caolu on 2016/11/5.
 */

public class MyAdapter extends BaseAdapter {

    private List<String> data;
    private Context mContext;

    public MyAdapter(Context context){
        mContext = context;
        data = new ArrayList<>();
        for(int i =0;i<10;i++){
            data.add("");
        }
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.findrents_gridview_item,
                    parent, false);
            holder.gridview = (PictureGridView) convertView
                    .findViewById(R.id.gridView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int num = position % data.size() + 1;//获取当前的图片数目

        int col = 1;//默认列数
        Log.i("tag", "num" + num);
        if (num == 1) {
            holder.gridview.setNumColumns(1);
            col = 1;
        } else if (num == 2 || num == 4) {
            holder.gridview.setNumColumns(2);
            col = 2;
        } else {
            holder.gridview.setNumColumns(3);
            col = 3;
        }

        holder.gridview.setAdapter(new MyGridViewAdapter(mContext, num, col));

        holder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    private class ViewHolder {
        PictureGridView gridview;
    }
}
