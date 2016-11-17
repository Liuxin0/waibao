package com.example.rental.discovery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rental.PImageView.zoomtransition.PicViewActivity;
import com.example.rental.R;
import com.example.rental.discovery.GridViewTest.WindowSize;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by caolu on 2016/11/5.
 */

public class DownGridViewAdapter extends BaseAdapter {

    private Context context;
    private int num;
    private int col;
    private List<String> data;

    public DownGridViewAdapter(Context context, int num) {
        this.context = context;
        this.num = num;
        this.col  = 3;
    }

    public void setData(List<String> data){
        this.data = data;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
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
        final ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        int width = WindowSize.getWidth(context);// 获取屏幕宽度
        Log.i("tag", "width" + width);
        int height = 0;
        width = width / col;// 对当前的列数进行设置imgView的宽度
        height = width;
        img.setLayoutParams(new AbsListView.LayoutParams(width, height));
        Picasso.with(context)
                .load(data.get(position))
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
                                Intent intent = new Intent(context, PicViewActivity.class);
                                Rect rect = new Rect();
                                img.getGlobalVisibleRect(rect);
                                intent.putExtra("url", data.get(position));
                                /**
                                 * 必要参数
                                 */
                                intent.putExtra("rect", rect);
                                intent.putExtra("scaleType", img.getScaleType());
                                intent.putExtra("width", bitmap.getWidth());
                                intent.putExtra("height", bitmap.getHeight());

                                context.startActivity(intent);
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
        return img;
    }
}
