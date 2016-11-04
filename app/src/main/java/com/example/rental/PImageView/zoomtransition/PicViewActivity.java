package com.example.rental.PImageView.zoomtransition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.ImageView;

import com.boycy815.pinchimageview.PinchImageView;
import com.example.rental.R;
import com.example.rental.homepage.FindHouseFragment;
import com.squareup.picasso.Picasso;


public class PicViewActivity extends Activity {

    private static final long ANIM_TIME = 200;

    private RectF mThumbMaskRect;
    private Matrix mThumbImageMatrix;

    private ObjectAnimator mBackgroundAnimator;

    private View mBackground;
    private PinchImageView mImageView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //获取参数
        /**
         * 参数，直接传入。
         */
        //ImageSource image = (ImageSource) getIntent().getSerializableExtra("image");
        //String memoryCacheKey =  getIntent().getStringExtra("cache_key");

        /**
         * 直接传入
         */
        final Rect rect = getIntent().getParcelableExtra("rect");

        /**
         * 直接传入
         */
        final ImageView.ScaleType scaleType = (ImageView.ScaleType) getIntent().getSerializableExtra("scaleType");

        /**
         * 宽高
         */
        final Point thumbSize = new Point(getIntent().getIntExtra("width",100),getIntent().getIntExtra("height",100));
        //final Point thumbSize = image.getSize(100, 100);

        /**
         * 不用
         */
        //ImageLoader imageLoader = Global.getImageLoader(getApplicationContext());
        //DisplayImageOptions originOptions = new DisplayImageOptions.Builder().build();

        //view初始化
        setContentView(R.layout.activity_pic_view);
        mImageView = (PinchImageView) findViewById(R.id.pic);
        mBackground = findViewById(R.id.background);
        /**
         * bitmap 公有成员，直接使用
         */
       // Bitmap bitmap = imageLoader.getMemoryCache().get(memoryCacheKey);
        Bitmap bitmap = FindHouseFragment.mBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            mImageView.setImageBitmap(bitmap);
        }

        url = getIntent().getStringExtra("url");
        /**
         * 直接使用bitmap 代替
         */
        mImageView.setImageBitmap(bitmap);
        /**
         * 使用Picasso 重新下载高质量图片加载到mImageView
         */
        Picasso.with(this)
                .load(url)
                .into(mImageView);
       // imageLoader.displayImage(image.getUrl(image.getOriginWidth(), image.getOriginHeight()), mImageView, originOptions);

        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mImageView.setAlpha(1f);

                //背景动画
                mBackgroundAnimator = ObjectAnimator.ofFloat(mBackground, "alpha", 0f, 1f);
                mBackgroundAnimator.setDuration(ANIM_TIME);
                mBackgroundAnimator.start();

                //status bar高度修正
                Rect tempRect = new Rect();
                mImageView.getGlobalVisibleRect(tempRect);
                rect.top = rect.top - tempRect.top;
                rect.bottom = rect.bottom - tempRect.top;

                //mask动画
                mThumbMaskRect = new RectF(rect);
                RectF bigMaskRect = new RectF(0, 0, mImageView.getWidth(), mImageView.getHeight());
                mImageView.zoomMaskTo(mThumbMaskRect, 0);
                mImageView.zoomMaskTo(bigMaskRect, ANIM_TIME);


                //图片放大动画
                RectF thumbImageMatrixRect = new RectF();
                PinchImageView.MathUtils.calculateScaledRectInContainer(new RectF(rect), thumbSize.x, thumbSize.y, scaleType, thumbImageMatrixRect);
                RectF bigImageMatrixRect = new RectF();
                PinchImageView.MathUtils.calculateScaledRectInContainer(new RectF(0, 0, mImageView.getWidth(), mImageView.getHeight()), thumbSize.x, thumbSize.y, ImageView.ScaleType.FIT_CENTER, bigImageMatrixRect);
                mThumbImageMatrix = new Matrix();
                PinchImageView.MathUtils.calculateRectTranslateMatrix(bigImageMatrixRect, thumbImageMatrixRect, mThumbImageMatrix);
                mImageView.outerMatrixTo(mThumbImageMatrix, 0);
                mImageView.outerMatrixTo(new Matrix(), ANIM_TIME);
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.playSoundEffect(SoundEffectConstants.CLICK);
                finish();
            }
        });
    }

    @Override
    public void finish() {
        if ((mBackgroundAnimator != null && mBackgroundAnimator.isRunning())) {
            return;
        }

        //背景动画
        mBackgroundAnimator = ObjectAnimator.ofFloat(mBackground, "alpha", mBackground.getAlpha(), 0f);
        mBackgroundAnimator.setDuration(ANIM_TIME);
        mBackgroundAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                PicViewActivity.super.finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mBackgroundAnimator.start();

        //mask动画
        mImageView.zoomMaskTo(mThumbMaskRect, ANIM_TIME);

        //图片缩小动画
        mImageView.outerMatrixTo(mThumbImageMatrix, ANIM_TIME);
    }
}