package com.example.rental.homepage;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.example.rental.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caolu on 2016/10/20.
 */
public class FindRentsFragment extends Fragment implements View.OnClickListener {

    private TextView occupationTextView, hobbyTextView; //大分类按钮
    private PopupWindow mPopupWindow1, mPopupWindow2;   //控件
    private View popuWindow1, popuWindow2;              //布局
    private int whereDisMiss = 0;                       //0;通过点击其他处隐藏，1:通过点击确定按钮隐藏

    private MyLinearLayouts[] mmLinearLayouts;          //大分类按键类数组
    private int mmNum = 2;                              //大分类按键数量


    private ListView mListView;
    private RentsAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.findrents_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initPopupWindow();
        initLayout();
        buttomTwoButtonClick();
        bounsView();
        initListView();
    }

    private void initListView() {
        mListView = (ListView)getActivity().findViewById(R.id.homepagemain_fragment1_listview);
        mAdapter = new RentsAdapter(getActivity());
        mListView.setAdapter(mAdapter);
        List<RentsBean> data = new ArrayList<>();
        for (int i =0;i<10;i++){
            if (i%2==0){
                RentsBean b = new RentsBean();
                b.setType(1);
                b.setName("shxy");
                data.add(b);
            }
            else
            {
                RentsBean b = new RentsBean();
                b.setType(0);
                b.setName("xxxx");
                data.add(b);
            }
        }
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }

    private void bounsView() {
        occupationTextView = (TextView) getActivity().findViewById(R.id.homepagemain_fragment_occupation);
        hobbyTextView = (TextView) getActivity().findViewById(R.id.homepagemain_fragment_hobby);
        occupationTextView.setOnClickListener(this);
        hobbyTextView.setOnClickListener(this);
    }

    private void initLayout() {
        mmLinearLayouts = new MyLinearLayouts[2];

        mmLinearLayouts[0] = new MyLinearLayouts();
        mmLinearLayouts[0].setLayoutCount(4);
        mmLinearLayouts[0].mLinears[0] = (LinearLayout) popuWindow1.findViewById(R.id.homepagerents_popup1_check1);
        mmLinearLayouts[0].mLinears[1] = (LinearLayout) popuWindow1.findViewById(R.id.homepagerents_popup1_check2);
        mmLinearLayouts[0].mLinears[2] = (LinearLayout) popuWindow1.findViewById(R.id.homepagerents_popup1_check3);
        mmLinearLayouts[0].mLinears[3] = (LinearLayout) popuWindow1.findViewById(R.id.homepagerents_popup1_check4);


        mmLinearLayouts[1] = new MyLinearLayouts();
        mmLinearLayouts[1].setLayoutCount(2);
        mmLinearLayouts[1].mLinears[0] = (LinearLayout) popuWindow2.findViewById(R.id.homepagerents_popup2_check1);
        mmLinearLayouts[1].mLinears[1] = (LinearLayout) popuWindow2.findViewById(R.id.homepagerents_popup2_check2);

        for (int i = 0; i < mmNum; i++) {
            for (int j = 0; j < mmLinearLayouts[i].mLinears.length; j++) {
                mmLinearLayouts[i].mLinears[j].setTag(new MyFlag());
            }
            mmLinearLayoutsSetOnClick(mmLinearLayouts[i]);
        }
    }

    private void initPopupWindow() {
        popuWindow1 = getActivity().getLayoutInflater().inflate(R.layout.homepagerents_popup1, null);
        mPopupWindow1 = new PopupWindow(popuWindow1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow1.setTouchable(true);
        mPopupWindow1.setOutsideTouchable(true);
        mPopupWindow1.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
        mPopupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (whereDisMiss == 0)
                    changeFlage(mmLinearLayouts[0].mLinears, 1);
            }
        });


        popuWindow2 = getActivity().getLayoutInflater().inflate(R.layout.homepagerents_popup2, null);
        mPopupWindow2 = new PopupWindow(popuWindow2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow2.setTouchable(true);
        mPopupWindow2.setOutsideTouchable(true);
        mPopupWindow2.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
        mPopupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (whereDisMiss == 0)
                    changeFlage(mmLinearLayouts[1].mLinears, 1);
            }
        });
    }

    private void mmLinearLayoutsSetOnClick(final MyLinearLayouts mm) {
        for (int i = 0; i < mm.mLinears.length; i++) {
            final int j = i;
            mm.mLinears[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeViewColor(mm.mLinears[j], true, 0);
                }
            });
        }
    }

    private void buttomTwoButtonClick() {
        TextView[] tvReset = new TextView[mmNum];
        tvReset[0] = (TextView) popuWindow1.findViewById(R.id.homepagerents_popup1_reset);
        tvReset[1] = (TextView) popuWindow2.findViewById(R.id.homepagerents_popup2_reset);

        for (int k = 0; k < mmNum; k++) {
            final int j = k;
            tvReset[k].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < mmLinearLayouts[j].mLinears.length; i++) {
                        MyFlag flag = (MyFlag) mmLinearLayouts[j].mLinears[i].getTag();
                        flag.right = 0;
                        mmLinearLayouts[j].mLinears[i].setTag(flag);
                        changeViewColor(mmLinearLayouts[j].mLinears[i], false, 1);
                    }
                }
            });
        }

        TextView tv_1_ok = (TextView) popuWindow1.findViewById(R.id.homepagerents_popup1_ok);
        tv_1_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whereDisMiss = 1;
                if (hasSelect(mmLinearLayouts[0].mLinears)) {
                    occupationTextView.setBackgroundResource(R.drawable.homepagerents_text_selectsome_selector);
                    occupationTextView.setTextColor(Color.RED);

                } else {
                    occupationTextView.setBackgroundResource(R.color.light_grey);
                    occupationTextView.setTextColor(Color.BLACK);
                }
                changeFlage(mmLinearLayouts[0].mLinears, 0);
                mPopupWindow1.dismiss();
                whereDisMiss = 0;
            }
        });


        TextView tv_2_ok = (TextView) popuWindow2.findViewById(R.id.homepagerents_popup2_ok);
        tv_2_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whereDisMiss = 1;
                if (hasSelect(mmLinearLayouts[1].mLinears)) {
                    hobbyTextView.setBackgroundResource(R.drawable.homepagerents_text_selectsome_selector);
                    hobbyTextView.setTextColor(Color.RED);
                    /**
                     * 增加网络请求，刷新listview
                     */
                } else {
                    hobbyTextView.setBackgroundResource(R.color.light_grey);
                    hobbyTextView.setTextColor(Color.BLACK);
                }
                changeFlage(mmLinearLayouts[1].mLinears, 0);
                mPopupWindow2.dismiss();
                whereDisMiss = 0;
            }
        });
    }


    private void changeViewColor(LinearLayout l, boolean setTag, int wred) {
        RelativeLayout r = (RelativeLayout) l.getChildAt(0);
        TextView t = (TextView) r.getChildAt(0);
        ImageView v = (ImageView) r.getChildAt(1);
        MyFlag flag = (MyFlag) l.getTag();
        if (flag.right == wred) {
            t.setTextColor(Color.RED);
            //t.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
            v.setImageResource(R.drawable.checkok);
            flag.right = 1;
        } else {
            t.setTextColor(Color.BLACK);
            v.setImageBitmap(null);
            flag.right = 0;
        }
        if (setTag) {
            l.setTag(flag);
        }
    }

    /**
     * 点击开启popupWindow之后 根据上次选择 显示正确的View
     *
     * @param m
     */
    private void checkLinearLayout(LinearLayout m[]) {
        for (int i = 0; i < m.length; i++) {
            changeViewColor(m[i], false, 1);
        }
    }

    /**
     * 改变LinearLayout中tag的左右值
     *
     * @param m
     * @param x
     */
    private void changeFlage(LinearLayout m[], int x) {
        if (x == 0) {
            for (int i = 0; i < m.length; i++) {
                MyFlag flag = (MyFlag) m[i].getTag();
                flag.left = flag.right;
                m[i].setTag(flag);
            }
        } else {
            for (int i = 0; i < m.length; i++) {
                MyFlag flag = (MyFlag) m[i].getTag();
                flag.right = flag.left;
                m[i].setTag(flag);
            }
        }
    }

    /**
     * 判断当前是否有按键被选中
     *
     * @param x
     * @return
     */
    private boolean hasSelect(LinearLayout x[]) {
        for (int i = 0; i < x.length; i++) {
            MyFlag flag = (MyFlag) x[i].getTag();
            if (flag.right == 1)
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.homepagemain_fragment_occupation:
                mPopupWindow1.showAsDropDown(view, 0, 10);
                checkLinearLayout(mmLinearLayouts[0].mLinears);
                break;
            case R.id.homepagemain_fragment_hobby:
                mPopupWindow2.showAsDropDown(view, 0, 10);
                checkLinearLayout(mmLinearLayouts[1].mLinears);
                break;
        }

    }

    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    /**
     * 辅助tag,帮助判断按钮状态
     */
    private class MyFlag {
        MyFlag() {
            left = 0;
            right = 0;
        }

        public int left;
        public int right;

    }

    class MyLinearLayouts {
        public MyLinearLayouts() {
            mLinears = null;
        }

        public LinearLayout[] mLinears;

        public void setLayoutCount(int n) {
            mLinears = new LinearLayout[n];
        }
    }

}
