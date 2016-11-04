package com.example.rental.homepage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rental.PImageView.zoomtransition.PicViewActivity;
import com.example.rental.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


/**
 * Created by caolu on 2016/10/20.
 */
public class FindHouseFragment extends Fragment {

    public static Bitmap mBitmap;
    private int width, height;


    private ImageView img;
    private String url = "http://cms.fn.img-space.com/t_s950x634/g1/M00/06/51/Cg-4rFbT34qIJMntAAHM2HI3G_wAAP8ZwHDMOUAAczw239.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.findhouse_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        img = (ImageView) getActivity().findViewById(R.id.homepagemain_fragment2_img);
        Picasso.with(getContext())
                .load(url)
                .resize(200, 200) //缩略图
                .centerCrop() //适配
                .placeholder(R.drawable.ic_launcher) //设置下载过程中的图片
                .error(R.drawable.checkok) //设置下载失败的图片
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        img.setImageBitmap(bitmap);
                        mBitmap = bitmap;
                        width = bitmap.getWidth();
                        height = bitmap.getWidth();
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), PicViewActivity.class);
                                //intent.putExtra("image",new MyImageSource(url,220,220));
                                //ImageSize targetSize = new ImageSize(thumbAware.getWidth(), thumbAware.getHeight());
                                //String memoryCacheKey = MemoryCacheUtils.generateKey(url, targetSize);
                                //intent.putExtra("cache_key", memoryCacheKey);
                                Rect rect = new Rect();
                                img.getGlobalVisibleRect(rect);
                                /**
                                 * 重新下载高质量图片
                                 */
                                intent.putExtra("url", url);
                                /**
                                 * 必要参数
                                 */
                                intent.putExtra("rect", rect);
                                intent.putExtra("scaleType", img.getScaleType());
                                intent.putExtra("width", width);
                                intent.putExtra("height", height);
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
