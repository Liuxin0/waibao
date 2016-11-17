package com.example.rental.discovery;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rental.R;
import com.example.rental.discovery.GridViewTest.PictureGridView;
import com.example.rental.model.DisInfoBean;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by caolu on 2016/11/17.
 */

public class ViewHolder {

    private TextView title,info,time,name;
    private ImageView photo;
    private PictureGridView mGridView;
    private DisInfoBean bean;

    private View view;
    public ViewHolder(View view){
        this.view = view;
    }

    public void bindView(DisInfoBean bean){
        this.bean = bean;
        initView();
        loadInfo();
    }

    private void loadInfo() {
        title.setText(bean.getTitle());
        name.setText(bean.getNickName());
        info.setText(bean.getInformation());
        Picasso.with(view.getContext())
                .load(bean.getUserPhotoEx())
                .placeholder(R.drawable.ic_launcher)
                .into(photo);
    }

    private void initView() {
        title = (TextView) view.findViewById(R.id.dis_title);
        time = (TextView) view.findViewById(R.id.dis_time);
        name = (TextView) view.findViewById(R.id.dis_name);
        info = (TextView) view.findViewById(R.id.dis_content);
        photo = (ImageView) view.findViewById(R.id.dis_photo);
        mGridView = (PictureGridView) view.findViewById(R.id.dis_gridview);
    }
}
