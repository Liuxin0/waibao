package com.example.rental.homepage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rental.PImageView.zoomtransition.PicViewActivity;
import com.example.rental.R;
import com.example.rental.model.RentInfoBean;
import com.example.rental.service.BaseService;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by caolu on 2016/11/16.
 */

public class RentDetailActivity extends Activity{

    private ImageView photo,img;
    private TextView name,time,address,content;
    private TextView label1,label2,label3;
    private RentInfoBean data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.rents_details);
        data = (RentInfoBean) getIntent().getSerializableExtra("data");
        initView();
        addContent();
    }

    private void addContent() {
        name.setText(data.getNickName());
        address.setText(data.getAddress());
        content.setText(data.getInformation());
/*        label1.setText(data.getLabel1());
        label2.setText(data.getLabel2());
        label3.setText(data.getLabel3());*/
        download_1();
        download_2();
    }


    private void initView() {
        photo = (ImageView) findViewById(R.id.derent_photo);
        img = (ImageView) findViewById(R.id.derent_img);
        name = (TextView) findViewById(R.id.derent_name);
        time = (TextView) findViewById(R.id.derent_time);
        address = (TextView) findViewById(R.id.derent_address);
        content = (TextView) findViewById(R.id.derent_content);
        label1 = (TextView) findViewById(R.id.derent_label1);
        label2 = (TextView) findViewById(R.id.derent_label2);
        label3 = (TextView) findViewById(R.id.derent_label3);
    }

    private void download_2() {
        Picasso.with(this)
                .load(data.getUserPhotoEx())
                .placeholder(R.drawable.ic_launcher)
                .into(photo);
    }

    private void download_1() {
        Picasso.with(this)
                .load(data.getPicture())
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
                                Intent intent = new Intent(RentDetailActivity.this, PicViewActivity.class);
                                Rect rect = new Rect();
                                img.getGlobalVisibleRect(rect);
                                intent.putExtra("url", data.getPictureEx());
                                /**
                                 * 必要参数
                                 */
                                intent.putExtra("rect", rect);
                                intent.putExtra("scaleType", img.getScaleType());
                                intent.putExtra("width", bitmap.getWidth());
                                intent.putExtra("height", bitmap.getHeight());

                                startActivity(intent);
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
