package com.example.rental.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rental.Map;
import com.example.rental.R;

/**
 * Created by caolu on 2016/10/4.
 */
public class HomepageMain extends Fragment{

    private Button toMap;
    private Button rents,house;
    private FragmentManager mManger;
    private FragmentTransaction mTransaction;
    private Fragment[] mFragments;
    private int nowFragment = 0;
    private RadioGroup group;


    private ImageView upInfo;
    private View popupView;
    private PopupWindow mPopupWindow;




    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepagemain_fragment, container, false);
        toMap= (Button) view.findViewById(R.id.tomap);
        toMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),Map.class);
                startActivity(intent);
            }
        });
        upInfo = (ImageView) view.findViewById(R.id.homepagemain_upinfo);
        initPopupWindow();

        return view;
    }

    private void initPopupWindow() {
        popupView = getActivity().getLayoutInflater().inflate(R.layout.homepagemain_upmenu,null);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.white));
        upInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.showAsDropDown(v,0,0);
            }
        });

        TextView tv1 = (TextView) popupView.findViewById(R.id.homepagemain_upmenu_tv1);
        TextView tv2 = (TextView) popupView.findViewById(R.id.homepagemain_upmenu_tv2);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                startActivity(new Intent(getActivity(),UpRentActivity.class));
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initFragment();
        group = (RadioGroup) getActivity().findViewById(R.id.rightradiobaba);
        rents = (Button) getActivity().findViewById(R.id.homepagemain_fragment_rents);
        house = (Button) getActivity().findViewById(R.id.homepagemain_fragment_house);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.homepagemain_fragment_rents:
                        if (nowFragment == 1)
                            return;
                        mTransaction = mManger.beginTransaction();
                        mTransaction.hide(mFragments[1]).show(mFragments[0]).commit();
                        nowFragment = 1;
                        break;

                    case R.id.homepagemain_fragment_house:
                        if (nowFragment == 2)
                            return;
                        mTransaction = mManger.beginTransaction();
                        mTransaction.hide(mFragments[0]).show(mFragments[1]).commit();
                        nowFragment = 2;
                        break;

                    default:
                        break;
                }
            }
        });

    }

    private void initFragment(){
        mFragments = new Fragment[2];
        mManger = getChildFragmentManager();
        mFragments[0] = mManger.findFragmentById(R.id.homepagemain_fragment11);
        mFragments[1] = mManger.findFragmentById(R.id.homepagemain_fragment22);
        mTransaction = mManger.beginTransaction();
        mTransaction.hide(mFragments[1]).show(mFragments[0]).commit();
        Log.i("t","t");
    }




}
