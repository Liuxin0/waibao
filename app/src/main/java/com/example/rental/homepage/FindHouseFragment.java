package com.example.rental.homepage;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rental.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by caolu on 2016/10/20.
 */
public class FindHouseFragment extends Fragment{

    private ImageView img;
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
                .load("http://7xrqhs.com1.z0.glb.clouddn.com/05d1646d45fb3bed2c9ca267156cff18.png")
                .resize(200,200)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher) //设置下载过程中的图片
                .error(R.drawable.checkok) //设置下载失败的图片
                .into(img);
    }
}
