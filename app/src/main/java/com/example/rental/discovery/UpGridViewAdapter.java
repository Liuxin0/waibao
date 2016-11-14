package com.example.rental.discovery;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rental.R;
import com.example.rental.discovery.GridViewTest.WindowSize;
import com.example.rental.util.PicassoImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.finalteam.galleryfinal.BuildConfig;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by caolu on 2016/11/5.
 */

public class UpGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private int num;
    private int col;
    private List<PhotoInfo> mList;
    private boolean mFirst = true;
    private static final int maxImage = 10;

    public UpGridViewAdapter(Context context, int num, int col) {
        this.mContext = context;
        this.num = num;
        this.col = col;
    }

    public void setImageList(List<PhotoInfo> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return num;
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
        ImageView img = new ImageView(mContext);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        int width = WindowSize.getWidth(mContext);// 获取屏幕宽度
        Log.i("tag", "width" + width);
        int height = 0;
        width = width / col;// 对当前的列数进行设置imgView的宽度
        if (num == 1) {
            width /= 3;
        }
        height = width;
        img.setLayoutParams(new AbsListView.LayoutParams(width, height));
        img.setImageResource(R.drawable.camera);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImg();
            }
        });
        mFirst = false;
        if (!mFirst) {

            Picasso.with(mContext)
                    .load(mList.get(position).getPhotoPath())
                    .into(img);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return img;
    }

    private void selectImg() {

        final ThemeConfig theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        FunctionConfig config = new FunctionConfig.Builder()
                .setMutiSelectMaxSize(maxImage)
                .build();

        //配置imageloader
        final PicassoImageLoader imageLoader = new PicassoImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(mContext, imageLoader, theme)
                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(config)
                .build();
        GalleryFinal.init(coreConfig);
        int REQUEST_CODE_GALLERY = 0;
        GalleryFinal.init(coreConfig);
        GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, config, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }
}
