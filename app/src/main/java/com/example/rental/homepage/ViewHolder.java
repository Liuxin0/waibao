package com.example.rental.homepage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rental.PImageView.zoomtransition.PicViewActivity;
import com.example.rental.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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

    public void bindView1(RentsBean bean) {
        nameTextView = (TextView) view.findViewById(R.id.homepagemain_fragment1_item1_name1);
        nameTextView.setText(bean.getName());
    }

    public void bindView2(final RentsBean bean) {
        nameTextView = (TextView) view.findViewById(R.id.homepagemain_fragment1_item1_name2);
        nameTextView.setText(bean.getName());
        img = (ImageView) view.findViewById(R.id.homepagemain_fragment1_item1_img2);
        //img.setImageResource(R.drawable.ic_launcher);
        Log.i("in","yes");
//        Log.i("url",bean.getImageUrl());
        Picasso.with(view.getContext())
                .load(bean.getImageUrl())
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                        Log.i("url",bean.getImageUrl());
                        img.setImageBitmap(bitmap);
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(view.getContext(), PicViewActivity.class);
                                Rect rect = new Rect();
                                img.getGlobalVisibleRect(rect);
                                /**
                                 * 重新下载高质量图片
                                 */
                                intent.putExtra("url", bean.getImageUrl());
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
