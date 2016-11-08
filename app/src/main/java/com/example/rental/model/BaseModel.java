package com.example.rental.model;

/**
 * Created by caolu on 2016/11/8.
 */

public class BaseModel {
    protected String msg;
    protected Integer state;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
