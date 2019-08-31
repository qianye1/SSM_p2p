package com.gxa.cdut.domain;

public class UserInfo {

    private int id;

    private String username;

    private String password;

    public static int STATE_ACTIVE = 0;

    public static int STATE_SLEEP = 1;

    private int state;

    private int usertype;

    public static int TYPE_USER = 0;

    public static int TYPE_MGR = 1;

    private int admin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

}
