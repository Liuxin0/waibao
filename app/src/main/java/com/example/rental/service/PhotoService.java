package com.example.rental.service;

import android.widget.Toast;

import com.example.rental.model.BaseModel;
import com.example.rental.model.UserModel;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;

/**
 * Created by caolu on 2016/11/7.
 */

public class PhotoService {
    public static void uploadFile(String url, RequestParams params, final Listener listener) throws Exception {
        AsyncHttpClient client = new AsyncHttpClient();
        // 上传文件
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                // 上传成功后要做的工作
                BaseModel model = new Gson().fromJson(new String(responseBody), BaseModel.class);
                listener.onSuccess(model);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
               /* UserModel model = new Gson().fromJson(new String(responseBody), UserModel.class);
                if (model != null) {
                    listener.onFailure(model.getMsg());
                } else {
                    listener.onFailure("上传失败");
                }*/
            }


            @Override
            public void onRetry(int retryNo) {
                // TODO Auto-generated method stub
                super.onRetry(retryNo);
                // 返回重试次数
            }

        });
    }
}
