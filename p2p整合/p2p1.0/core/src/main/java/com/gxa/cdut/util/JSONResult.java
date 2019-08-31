package com.gxa.cdut.util;



public class JSONResult {
    //成功的状态
    private boolean success = true;
    //返回的信息
    private String msg;

    public JSONResult() {
    }

/*    public JSONResult(String msg) {
        this.msg = msg;
    }

    public JSONResult(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }*/

    public boolean getSuccess() {

        return success;
    }

    public void setSuccess(boolean success) {

        this.success = success;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }
}
