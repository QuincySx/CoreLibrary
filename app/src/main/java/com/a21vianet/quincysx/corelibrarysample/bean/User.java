package com.a21vianet.quincysx.corelibrarysample.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wang.rongqiang on 2017/5/5.
 */

public class User {

    @SerializedName("user_id")
    private int userId;
    @SerializedName("mobile")
    private int mobile;
    @SerializedName("name")
    private int name;
    @SerializedName("thumb")
    private int thumb;
    @SerializedName("token")
    private int token;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", mobile=" + mobile +
                ", name=" + name +
                ", thumb=" + thumb +
                ", token=" + token +
                '}';
    }
}
