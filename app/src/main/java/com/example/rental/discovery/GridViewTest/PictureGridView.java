package com.example.rental.discovery.GridViewTest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by caolu on 2016/11/5.
 */

public class PictureGridView extends GridView{
    public PictureGridView(Context context) {
        this(context,null);
    }

    public PictureGridView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PictureGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
