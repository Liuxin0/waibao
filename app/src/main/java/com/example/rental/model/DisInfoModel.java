package com.example.rental.model;

import java.util.List;

/**
 * Created by caolu on 2016/11/17.
 */

public class DisInfoModel extends BaseModel{
    private List<DisInfoBean> circledata;

    public List<DisInfoBean> getCircledata() {
        return circledata;
    }

    public void setCircledata(List<DisInfoBean> circledata) {
        this.circledata = circledata;
    }
}
