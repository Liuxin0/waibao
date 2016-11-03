package com.example.rental.homepage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rental.R;

/**
 * Created by caolu on 2016/11/1.
 */
public class ViewHolder {
    public View view;

    private TextView nameTextView;
    private ImageView img;

    public ViewHolder(View v) {
        view = v;
    }

    public void bindView1(RentsBean bean){
        nameTextView = (TextView) view.findViewById(R.id.homepagemain_fragment1_item1_name1);
        nameTextView.setText(bean.getName());
    }

    public void bindView2(RentsBean bean){
        nameTextView = (TextView) view.findViewById(R.id.homepagemain_fragment1_item1_name2);
        nameTextView.setText(bean.getName());
        img = (ImageView) view.findViewById(R.id.homepagemain_fragment1_item1_img2);
        img.setImageResource(R.drawable.ic_launcher);
    }

}
