package com.gxa.cdut.domain;

import java.util.Date;

public class MailVerify {
    private int id;
    private int logininfo_id;
    private String email;
    private Date sendTime;
    private String uuid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLogininfo_id() {
        return logininfo_id;
    }

    public void setLogininfo_id(int logininfo_id) {
        this.logininfo_id = logininfo_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
