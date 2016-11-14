package com.example.rental.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.example.rental.MyActivity;
import com.example.rental.R;
import com.example.rental.App;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

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
        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        Log.i("MyActivity token", token);
        connect(token);
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

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(String token) {
        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {

                    Log.d("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {

                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MyActivity.class);
                    startActivity(intent);
                    finish();
                    Log.d("LoginActivity", "--onSuccess" + userid);

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }
}
