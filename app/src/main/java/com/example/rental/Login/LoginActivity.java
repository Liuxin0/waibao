package com.example.rental.Login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;
import com.example.rental.MyActivity;
import com.example.rental.R;

/**
 * 登录注册界面
 * Created by 1111 on 2016/10/19.
 */
public class LoginActivity extends FragmentActivity implements
        FindPasswordFragment.FFindClickListener,
        LoginFragment.FLoginBtnClick,
        RegisterFragment.FRegisterClickListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment[] mfragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int currentapiVersion = Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.KITKAT) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        setContentView(R.layout.login_main);

        if (savedInstanceState == null) {
            init();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.login_frame, mfragment[0]).commit();
        }
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        mfragment = new Fragment[4];
        mfragment[0] = new LoginFragment();
        mfragment[1] = new RegisterFragment();
        mfragment[2] = new FindPasswordFragment();
    }

    @Override
    public void onFFindBackClick() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(mfragment[2]).show(mfragment[0]).addToBackStack("FindBack").commit();
    }

    @Override
    public void onFLoginTrue() {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MyActivity.class);
        startActivity(intent);
    }

    @Override
    public void onToRegisterClick() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(mfragment[0]).add(R.id.login_frame, mfragment[1]).addToBackStack("ToRe").commit();
    }

    @Override
    public void onToFindClick() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(mfragment[0]).add(R.id.login_frame, mfragment[2]).addToBackStack("ToFind").commit();
    }

    @Override
    public void onFRegisterClick() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(mfragment[1]).show(mfragment[0]).addToBackStack("ReBack").commit();
    }
}
