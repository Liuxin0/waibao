package com.example.rental.homepage;

import android.app.Activity;
import android.graphics.Color;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rental.R;
import com.example.rental.model.RentInfoBean;
import com.example.rental.model.RentInfoModel;
import com.example.rental.service.Listener;
import com.example.rental.service.RentInfoService;
import com.example.rental.util.BaseUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caolu on 2016/10/20.
 */
public class FindRentsFragment extends Fragment implements View.OnClickListener {

    private TextView occupationTextView, hobbyTextView, numTextView; //大分类按钮
    private PopupWindow mPopupWindow1, mPopupWindow2, mPopupWindow3;   //控件
    private View popuWindow1, popuWindow2, popuWindow3;              //布局
    private int whereDisMiss = 0;                       //0;通过点击其他处隐藏，1:通过点击确定按钮隐藏

    private MyLinearLayouts[] mmLinearLayouts;          //大分类按键类数组
    private int mmNum = 3;                              //大分类按键数量

    private final String[][] mT = new String[][]{{"程序员", "公务员", "教师", "学生"}, {"写代码", "旅游"}, {"2人", "3人", "4人", "4人及以上"}};

    private PullToRefreshListView mListView;
    private RentsAdapter mAdapter;
    private static final String mUrl = BaseUtil.BASE_URL+"hezuinfors";
    private int page = 1;          //上拉加载页面
    private List<RentInfoBean> mData;
    private String[] exUrls;
    private boolean mFirst = true;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.findrents_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initExUrl();
        initPopupWindow();
        initLayout();
        buttomTwoButtonClick();
        bounsView();
        initListView();
        downloadInfo(mFirst, 0);
    }

    private void initExUrl() {
        exUrls = new String[mmNum];
        for (int i = 0; i < mmNum; i++) {
            exUrls[i] = "";
        }
    }

    /**
     * @param first 第一次设置adapter 否则刷新数据
     * @param mode  mode = 0;下拉刷新，mode = 1;上拉加载。
     */
    private void downloadInfo(final boolean first, final int mode) {
        RequestParams param = new RequestParams();
        param.put("page", page);
        param.put("Label1", exUrls[0]);
        param.put("Label2", exUrls[1]);
        param.put("Label3", exUrls[2]);
        Log.i("exx", exUrls[0] + "*");
        Log.i("exx", exUrls[1] + "*");
        Log.i("exx", exUrls[2] + "*");

        try {
            RentInfoService.get(mUrl, param, new Listener() {
                @Override
                public void onSuccess(Object object) {
                    RentInfoModel model = (RentInfoModel) object;
                    //mData = model.getHezudata();
                    if (model == null) {
                        Toast.makeText(getContext(), "错误", Toast.LENGTH_SHORT).show();
                    } else {
                        if (model.getState() == 1) {
                            if (mode == 1)
                                mData.addAll(model.getHezudata());
                            else {
                                mData = model.getHezudata();
                            }
                            mAdapter.setData(mData);
                            if (first) {
                                mListView.setAdapter(mAdapter);
                                mFirst = false;
                            } else {
                                mAdapter.notifyDataSetChanged();
                            }
                            Log.i("refresh", "yes");
                        } else {
                            Toast.makeText(getContext(), model.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    mListView.onRefreshComplete();
                }

                @Override
                public void onFailure(String msg) {
                    Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    if (mode == 1)
                        page--;
                    mListView.onRefreshComplete();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListView() {
        mListView = (PullToRefreshListView) getActivity().findViewById(R.id.homepagemain_fragment1_listview);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (!BaseUtil.isNetworkConnected(getActivity())) {
                    Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                    mListView.onRefreshComplete();
                }
                page = 1;
                downloadInfo(mFirst, 0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (!BaseUtil.isNetworkConnected(getActivity())) {
                    Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                    mListView.onRefreshComplete();
                }
                page++;
                downloadInfo(mFirst, 1);
            }
        });
        mAdapter = new RentsAdapter(getActivity());
    }

    /**
     * 下面两个按键
     */
    private void bounsView() {
        occupationTextView = (TextView) getActivity().findViewById(R.id.homepagemain_fragment_occupation);
        hobbyTextView = (TextView) getActivity().findViewById(R.id.homepagemain_fragment_hobby);
        numTextView = (TextView) getActivity().findViewById(R.id.homepagemain_fragment_num);
        numTextView.setOnClickListener(this);
        occupationTextView.setOnClickListener(this);
        hobbyTextView.setOnClickListener(this);
    }

    private void initLayout() {
        mmLinearLayouts = new MyLinearLayouts[mmNum];

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

        mmLinearLayouts[2] = new MyLinearLayouts();
        mmLinearLayouts[2].setLayoutCount(4);
        mmLinearLayouts[2].mLinears[0] = (LinearLayout) popuWindow3.findViewById(R.id.homepagerents_popup3_check1);
        mmLinearLayouts[2].mLinears[1] = (LinearLayout) popuWindow3.findViewById(R.id.homepagerents_popup3_check2);
        mmLinearLayouts[2].mLinears[2] = (LinearLayout) popuWindow3.findViewById(R.id.homepagerents_popup3_check3);
        mmLinearLayouts[2].mLinears[3] = (LinearLayout) popuWindow3.findViewById(R.id.homepagerents_popup3_check4);

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

        popuWindow3 = getActivity().getLayoutInflater().inflate(R.layout.homepagerents_popup3, null);
        mPopupWindow3 = new PopupWindow(popuWindow3, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow3.setTouchable(true);
        mPopupWindow3.setOutsideTouchable(true);
        mPopupWindow3.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
        mPopupWindow3.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (whereDisMiss == 0)
                    changeFlage(mmLinearLayouts[2].mLinears, 1);
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

    /**
     * 下面两个按键的监听事件
     */
    private void buttomTwoButtonClick() {
        TextView[] tvReset = new TextView[mmNum];
        tvReset[0] = (TextView) popuWindow1.findViewById(R.id.homepagerents_popup1_reset);
        tvReset[1] = (TextView) popuWindow2.findViewById(R.id.homepagerents_popup2_reset);
        tvReset[2] = (TextView) popuWindow3.findViewById(R.id.homepagerents_popup3_reset);
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
                    exUrls[0] = addUrl(mmLinearLayouts[0], 0);
                    page = 0;
                    downloadInfo(mFirst, 0);
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

                    exUrls[1] = addUrl(mmLinearLayouts[1], 1);
                    page = 0;
                    downloadInfo(mFirst, 0);
                } else {
                    hobbyTextView.setBackgroundResource(R.color.light_grey);
                    hobbyTextView.setTextColor(Color.BLACK);
                }
                changeFlage(mmLinearLayouts[1].mLinears, 0);
                mPopupWindow2.dismiss();
                whereDisMiss = 0;
            }
        });

        TextView tv_3_ok = (TextView) popuWindow3.findViewById(R.id.homepagerents_popup3_ok);
        tv_3_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whereDisMiss = 1;
                if (hasSelect(mmLinearLayouts[2].mLinears)) {
                    numTextView.setBackgroundResource(R.drawable.homepagerents_text_selectsome_selector);
                    numTextView.setTextColor(Color.RED);
                    exUrls[2] = addUrl(mmLinearLayouts[2], 2);
                    page = 0;
                    downloadInfo(mFirst, 0);
                } else {
                    numTextView.setBackgroundResource(R.color.light_grey);
                    numTextView.setTextColor(Color.BLACK);
                }
                changeFlage(mmLinearLayouts[2].mLinears, 0);
                mPopupWindow3.dismiss();
                whereDisMiss = 0;
            }
        });
    }

    private String addUrl(MyLinearLayouts mm, int which) {
        String temp = "";
        for (int i = 0; i < mm.mLinears.length; i++) {
            MyFlag flag = (MyFlag) mm.mLinears[i].getTag();
            if (flag.right == 1) {
                temp = temp + mT[which][i] + "+";
            }
        }
        return temp;
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
            case R.id.homepagemain_fragment_num:
                mPopupWindow3.showAsDropDown(view, 0, 10);
                checkLinearLayout(mmLinearLayouts[2].mLinears);
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
