package com.example.rental.discovery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.rental.R;
import com.example.rental.homepage.*;
import com.example.rental.model.DisInfoBean;
import com.example.rental.model.DisInfoModel;

import java.util.List;

/**
 * Created by caolu on 2016/11/17.
 */

public class DisAdapter extends BaseAdapter {

    private Context mContext;
    private List<DisInfoBean> data;

    public DisAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<DisInfoBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        convertView = LayoutInflater.from(mContext).inflate(R.layout.dis_item, parent, false);
        holder = new ViewHolder(convertView);
        holder.bindView(data.get(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,RentDetailActivity.class);
                intent.putExtra("data",data.get(position));
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
}
