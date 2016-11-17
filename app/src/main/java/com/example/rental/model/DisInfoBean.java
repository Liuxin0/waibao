package com.example.rental.model;

import java.io.Serializable;

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
    private String Picture1;
    private String Picture2;
    private String Picture3;
    private String Picture4;
    private String Picture5;
    private String Picture6;
    private String Picture7;
    private String Picture8;
    private String Picture9;

    public String getPicture1() {
        return Picture1;
    }

    public void setPicture1(String picture1) {
        Picture1 = picture1;
    }

    public String getPicture2() {
        return Picture2;
    }

    public void setPicture2(String picture2) {
        Picture2 = picture2;
    }

    public String getPicture3() {
        return Picture3;
    }

    public void setPicture3(String picture3) {
        Picture3 = picture3;
    }

    public String getPicture4() {
        return Picture4;
    }

    public void setPicture4(String picture4) {
        Picture4 = picture4;
    }

    public String getPicture5() {
        return Picture5;
    }

    public void setPicture5(String picture5) {
        Picture5 = picture5;
    }

    public String getPicture6() {
        return Picture6;
    }

    public void setPicture6(String picture6) {
        Picture6 = picture6;
    }

    public String getPicture7() {
        return Picture7;
    }

    public void setPicture7(String picture7) {
        Picture7 = picture7;
    }

    public String getPicture8() {
        return Picture8;
    }

    public void setPicture8(String picture8) {
        Picture8 = picture8;
    }

    public String getPicture9() {
        return Picture9;
    }

    public void setPicture9(String picture9) {
        Picture9 = picture9;
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
