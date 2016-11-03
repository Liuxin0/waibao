package com.example.rental.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.rental.model.UserModel;
import com.example.rental.util.HttpRequest;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;


/**yFF1ggUz
 * Created by 1111 on 2016/10/20.
 */
public class UserService {
    public void get(Context context, String url, RequestParams params, final Listener listener){
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Log.i("json",new String(bytes));
                UserModel model=new Gson().fromJson(new String(bytes),UserModel.class);
                listener.onSuccess(model);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                UserModel model=new Gson().fromJson(new String(bytes),UserModel.class);
                listener.onFailure(model.getMsg());
            }
        });
    }

    public void post(Context context, String url, RequestParams params, final Listener listener){
        HttpRequest.post(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                UserModel model=new Gson().fromJson(new String(bytes),UserModel.class);
                listener.onSuccess(model);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
 //               Log.e("s",new String(bytes));
//                UserModel model=new Gson().fromJson(new String(bytes),UserModel.class);
//                listener.onFailure(model.getMsg());
            }
        });
    }
}
