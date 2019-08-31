package com.gxa.cdut.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Iplog {

    private int id;
    private String ip;
    private int state;
    private String username;
    private int loginInfoId;
    private int userType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loginTime;

    public static int LOGIN_SUCCESS = 0;
    public static int LOGIN_FAIL = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(int loginInfoId) {
        this.loginInfoId = loginInfoId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getLoginTime() {
        return loginTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
