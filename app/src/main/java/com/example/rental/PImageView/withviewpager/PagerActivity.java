package com.example.rental.PImageView.withviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.boycy815.pinchimageview.PinchImageView;
import com.example.rental.PImageView.Global;
import com.example.rental.PImageView.images.ImageSource;
import com.example.rental.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.LinkedList;


public class PagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        final LinkedList<PinchImageView> viewCache = new LinkedList<PinchImageView>();
        final DisplayImageOptions thumbOptions = new DisplayImageOptions.Builder().resetViewBeforeLoading(true).cacheInMemory(true).build();
        final DisplayImageOptions originOptions = new DisplayImageOptions.Builder().build();

        final PinchImageViewPager pager = (PinchImageViewPager) findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Global.getTestImagesCount();
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PinchImageView piv;
                if (viewCache.size() > 0) {
                    piv = viewCache.remove();
                    piv.reset();
                } else {
                    piv = new PinchImageView(PagerActivity.this);
                }
                ImageSource image = Global.getTestImage(position);
                Global.getImageLoader(getApplicationContext()).displayImage(image.getUrl(100, 100), piv, thumbOptions);
                container.addView(piv);
                return piv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                PinchImageView piv = (PinchImageView) object;
                container.removeView(piv);
                viewCache.add(piv);
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                PinchImageView piv = (PinchImageView) object;
                ImageSource image = Global.getTestImage(position);
                Global.getImageLoader(getApplicationContext()).displayImage(image.getUrl(image.getOriginWidth(), image.getOriginHeight()), piv, originOptions);
                pager.setMainPinchImageView(piv);
            }
        });
    }
}