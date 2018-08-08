package com.lena.asp.Entity;

import java.io.Serializable;

/**
 * @author lilingfei
 * @date 2018/8/7
 */
public class Person implements Serializable {
    private String userId;
    private String name;
    private String userImg;

    public String getUserId() {
        return userId == null ? "" : userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserImg() {
        return userImg == null ? "" : userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
