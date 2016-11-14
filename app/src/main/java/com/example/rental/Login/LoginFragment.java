package com.example.rental.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rental.R;
import com.example.rental.model.UserModel;
import com.example.rental.service.Listener;
import com.example.rental.service.UserService;
import com.example.rental.util.Md5;
import com.loopj.android.http.RequestParams;

/**
 * Created by 1111 on 2016/10/19.
 */
public class LoginFragment extends Fragment {

    public interface FLoginBtnClick {
        void onFLoginTrue();

        void onToRegisterClick();

        void onToFindClick();
    }

    private Button loginButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView toRegister;
    private TextView toFind;
    private FLoginBtnClick fLoginBtnClick;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }


    private void init() {
        usernameEditText = (EditText) getActivity().findViewById(R.id.login_zhanghao);
        passwordEditText = (EditText) getActivity().findViewById(R.id.login_password);
        toFind = (TextView) getActivity().findViewById(R.id.login_to_losspass);
        loginButton = (Button) getActivity().findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        toFind = (TextView) getActivity().findViewById(R.id.login_to_losspass);
        toFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof FLoginBtnClick) {
                    ((FLoginBtnClick) getActivity()).onToFindClick();
                }
            }
        });

        toRegister = (TextView) getActivity().findViewById(R.id.login_to_register);
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof FLoginBtnClick) {
                    ((FLoginBtnClick) getActivity()).onToRegisterClick();
                }
            }
        });
    }

    private void login() {
        final String userphone = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        if (userphone.length() <= 0 || password.length() <= 0) {
            Toast.makeText(getActivity(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String Md5_password = Md5.getMD5(password);
        RequestParams requestParams = new RequestParams();
        requestParams.add("UserPhone", userphone);
        requestParams.add("PassWord", Md5_password);
        UserService service = new UserService();
        String url = "login/";
        service.post(getActivity(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {
                UserModel userModel = (UserModel) object;
                Integer state = userModel.getState();
                String SecretKey = userModel.getSecretKey();
                token = userModel.getToken();
                Log.i("LoginFragment", "token = " + token);
//                Toast.makeText(getActivity(),state,Toast.LENGTH_SHORT).show();
                if (state == 1) {
                    saveInfor(userphone, password, SecretKey);
//                    new LoginAsyncTask().execute(userModel.getUserPhoto());

                    if (getActivity() instanceof FLoginBtnClick) {
                        ((FLoginBtnClick) getActivity()).onFLoginTrue();
                    }
                } else {
                    Toast.makeText(getActivity(), "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), "与服务器请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveInfor(String phone, String password, String SecretKey) {
        SharedPreferences preferences = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("UserPhone", phone);
        editor.putString("SecretKey", SecretKey);
        editor.putString("PassWord", password);
        editor.putString("token", token);
        if (!editor.commit()) {
            System.err.println("！！！写入失败！！！");
        }
    }

}
