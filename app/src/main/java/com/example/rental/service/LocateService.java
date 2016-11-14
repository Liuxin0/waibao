package com.example.rental.service;

import android.content.Context;
import android.util.Log;

import com.example.rental.model.LocateModel;
import com.example.rental.util.HttpRequest;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

/**
 * Created by 1111 on 2016/11/10.
 */

public class LocateService {
    public void get(Context context, String url, RequestParams params, final Listener listener){
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Log.i("json",new String(bytes));
                LocateModel model=new Gson().fromJson(new String(bytes),LocateModel.class);
                listener.onSuccess(model);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                LocateModel model=new Gson().fromJson(new String(bytes),LocateModel.class);
                listener.onFailure(model.getMsg());
            }
        });
    }

    public void post(Context context, String url, RequestParams params, final Listener listener){
        HttpRequest.post(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Log.i("post",new String(bytes));
                LocateModel model=new Gson().fromJson(new String(bytes),LocateModel.class);
                listener.onSuccess(model);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.i("post",new String(bytes));
                //               Log.e("s",new String(bytes));
//                LocateModel model=new Gson().fromJson(new String(bytes),LocateService.class);
//                listener.onFailure(model.getMsg());
            }
        });
    }
}
