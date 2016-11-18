package com.example.rental.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caolu on 2016/11/17.
 */

public class DisInfoBean implements Serializable{
    private Integer UserId;
    private String Information;
    private String Title;
    private String NickName;
    private String UserPhoto;
    private String UserPhotoEx;
    private List<String> Picture;

    public List<String> getPicture() {
        return Picture;
    }

    public void setPicture(List<String> picture) {
        Picture = picture;
    }

    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }

    public String getUserPhotoEx() {
        return UserPhotoEx;
    }

    public void setUserPhotoEx(String userPhotoEx) {
        UserPhotoEx = userPhotoEx;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}
