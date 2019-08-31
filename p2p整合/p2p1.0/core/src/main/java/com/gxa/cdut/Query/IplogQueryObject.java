package com.gxa.cdut.Query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class IplogQueryObject extends QueryObject {

    private String username;

    private Date beginDate;
    private Date endDate;
    private int state;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

}
