package com.example.rental.model;

import java.util.List;

/**
 * Created by caolu on 2016/11/8.
 */

public class RentInfoModel extends BaseModel{
    private List<RentInfoBean> hezudata;

    public List<RentInfoBean> getHezudata() {
        return hezudata;
    }

    public void setHezudata(List<RentInfoBean> hezudata) {
        this.hezudata = hezudata;
    }
}
