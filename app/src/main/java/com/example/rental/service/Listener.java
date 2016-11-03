package com.example.rental.service;

/**
 * Created by 1111 on 2016/10/20.
 */
public interface Listener {
    public void onSuccess(Object object);
    public void onFailure(String msg);
}