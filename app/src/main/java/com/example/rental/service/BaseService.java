package com.example.rental.service;

import android.util.Log;

import com.example.rental.model.BaseModel;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

/**
 * Created by caolu on 2016/11/7.
 */

public class BaseService {
    public static void post(String url, RequestParams params, final Listener listener) throws Exception {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(100000);
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                // 上传成功后要做的工作
                Log.i("json",new String(responseBody));
                BaseModel model = new Gson().fromJson(new String(responseBody), BaseModel.class);
                listener.onSuccess(model);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                listener.onFailure("网络错误");
            }


            @Override
            public void onRetry(int retryNo) {
                // TODO Auto-generated method stub
                super.onRetry(retryNo);
                // 返回重试次数
            }

        });
    }
    public static void get(String url, RequestParams params, final Listener listener) throws Exception {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                // 上传成功后要做的工作
                //BaseModel model = new Gson().fromJson(new String(responseBody), BaseModel.class);
                listener.onSuccess(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                listener.onFailure("网络错误");
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
