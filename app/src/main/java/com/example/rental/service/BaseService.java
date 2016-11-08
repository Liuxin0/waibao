package com.example.rental.service;

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
                if (responseBody ==null){
                    listener.onFailure("网络错误");
                }else{
                    BaseModel model = new Gson().fromJson(new String(responseBody), BaseModel.class);
                    if (model == null){
                        listener.onFailure("网络错误");
                    }else
                    {
                        listener.onFailure(model.getMsg());
                    }
                }
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