package com.example.rental.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.rental.R;
import com.example.rental.model.RentInfoBean;

import java.util.List;

/**
 * Created by caolu on 2016/11/1.
 */
public class RentsAdapter extends BaseAdapter {

    private List<RentInfoBean> data;
    private Context mContext;
    public RentsAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<RentInfoBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        convertView = LayoutInflater.from(mContext).inflate(R.layout.findrents_item, parent, false);
        holder = new ViewHolder(convertView);
        holder.bindView1(data.get(position));
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
