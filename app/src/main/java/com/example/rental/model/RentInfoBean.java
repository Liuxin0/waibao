package com.example.rental.model;

/**
 * Created by caolu on 2016/11/8.
 */

public class RentInfoBean {
    private String UserName;
    private String SecretKey;
    private String Information ;
    private String Address ;
    private String Picture;
    private Integer Number;
    private String UserPhoto;
    private String NickName;
    private Integer Label1;
    private Integer Label2;
    private Integer Label3;
    private String PictureEx;

    public String getPictureEx() {
        return PictureEx;
    }

    public void setPictureEx(String pictureEx) {
        PictureEx = pictureEx;
    }

    public Integer getLabel1() {
        return Label1;
    }

    public void setLabel1(Integer label1) {
        Label1 = label1;
    }

    public Integer getLabel2() {
        return Label2;
    }

    public void setLabel2(Integer label2) {
        Label2 = label2;
    }

    public Integer getLabel3() {
        return Label3;
    }

    public void setLabel3(Integer label3) {
        Label3 = label3;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }
}
