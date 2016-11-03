package com.example.rental.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.rental.R;

import java.util.List;

/**
 * Created by caolu on 2016/11/1.
 */
public class RentsAdapter extends BaseAdapter {

    private static final int TYPE_1 = 0;
    private static final int TYPE_2 = 1;
    private int typeCount = 2;
    private List<RentsBean> data;
    private Context mContext;

    public RentsAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<RentsBean> data) {
        this.data = data;
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
    public int getViewTypeCount() {
        return typeCount;
    }

    @Override
    public int getItemViewType(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        switch (data.get(position).getType()){
            case TYPE_1:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.findrents_item1, parent, false);
                holder = new ViewHolder(convertView);
                holder.bindView1(data.get(position));
                break;
            case TYPE_2:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.findrents_item2, parent, false);
                holder = new ViewHolder(convertView);
                holder.bindView2(data.get(position));
                break;
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }


}
