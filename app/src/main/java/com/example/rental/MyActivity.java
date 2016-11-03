package com.example.rental;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.example.rental.view.TopLinearlayout;

public class MyActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */

    private Fragment[] mFragments;
    private FragmentManager mFragmentManger;
    private FragmentTransaction mTransaction;
    private TopLinearlayout mTopLinearlayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.main);

        initFragment();
        mTopLinearlayout.setFragmentEvent(mFragmentManger,mTransaction,mFragments);


    }

    private void initFragment(){
        mTopLinearlayout =  (TopLinearlayout) findViewById(R.id.myactivity_topview);
        mFragmentManger = getSupportFragmentManager();
        mFragments = new Fragment[4];

        mFragments[0] = mFragmentManger.findFragmentById(R.id.main_fragment1);
        mFragments[1] = mFragmentManger.findFragmentById(R.id.main_fragment2);
        mFragments[2] = mFragmentManger.findFragmentById(R.id.main_fragment3);
        mFragments[3] = mFragmentManger.findFragmentById(R.id.main_fragment4);
        mTransaction = mFragmentManger.beginTransaction();
        mTransaction.hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3])
                .show(mFragments[0]).commit();
    }


}
