package com.example.rental.homepage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rental.PImageView.zoomtransition.PicViewActivity;
import com.example.rental.R;
import com.example.rental.model.RentInfoBean;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by caolu on 2016/11/1.
 */
public class ViewHolder {

    private final String[] TYPE_1 = new String[]{"学生", "教师", "程序员"};
    private final String[] TYPE_2 = new String[]{"学生", "教师", "程序员"};
    public View view;
    private TextView nameTextView, addressTextView, label1TextView, label2TextView, infoTextView;
    private ImageView img, photo;
    private RentInfoBean bean;


    public ViewHolder(View v) {
        view = v;
    }

    public void bindView1(final RentInfoBean bean) {
        this.bean = bean;
        infoTextView = (TextView) view.findViewById(R.id.homepagemain_fragment1_item1_info);
        nameTextView = (TextView) view.findViewById(R.id.homepagemain_fragment1_item1_name);
        addressTextView = (TextView) view.findViewById(R.id.homepagemain_fragment1_item1_address);
        label1TextView = (TextView) view.findViewById(R.id.homepagemain_fragment1_item1_label1);
        label2TextView = (TextView) view.findViewById(R.id.homepagemain_fragment1_item1_label2);
        img = (ImageView) view.findViewById(R.id.homepagemain_fragment1_item1_img);
        photo = (ImageView) view.findViewById(R.id.homepagemain_fragment1_item1_photo);

        String info = bean.getInformation();
        if (info.length() > 40) {
            info = info.substring(0, 40);
        }
        infoTextView.setText(info);
        nameTextView.setText(bean.getNickName());
        addressTextView.setText(bean.getAddress());
        label1TextView.setText(TYPE_1[bean.getLabel1()]);
        label2TextView.setText(TYPE_1[bean.getLabel2()]);
        download_1();
        download_2();
    }

    private void download_2() {
        Log.i("ex", bean.getUserPhotoEx());
        Picasso.with(view.getContext())
                .load(bean.getUserPhotoEx())
                .placeholder(R.drawable.ic_launcher)
                .into(photo);
    }

    private void download_1() {
        Picasso.with(view.getContext())
                .load(bean.getPicture())
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                        img.setImageBitmap(bitmap);
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(view.getContext(), PicViewActivity.class);
                                Rect rect = new Rect();
                                img.getGlobalVisibleRect(rect);
                                intent.putExtra("url", bean.getPictureEx());
                                /**
                                 * 必要参数
                                 */
                                intent.putExtra("rect", rect);
                                intent.putExtra("scaleType", img.getScaleType());
                                intent.putExtra("width", bitmap.getWidth());
                                intent.putExtra("height", bitmap.getHeight());

                                view.getContext().startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }
}
