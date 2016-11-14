package com.example.rental.model;

/**
 * Created by 1111 on 2016/10/20.
 */
public class UserModel {
    private String msg;
    private Integer state;
    private String UserId;
    private String PassWord;
    private String UserPhoto;
    private String NickName;
    private String Address;
    private String UserPhone;
    private String Label1;
    private String Label2;
    private String Label3;
    private String SecretKey;
    private String token;

    public UserModel(){}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getLabel1() {
        return Label1;
    }

    public void setLabel1(String label1) {
        Label1 = label1;
    }

    public String getLabel2() {
        return Label2;
    }

    public void setLabel2(String label2) {
        Label2 = label2;
    }

    public String getLabel3() {
        return Label3;
    }

    public void setLabel3(String label3) {
        Label3 = label3;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }
}
