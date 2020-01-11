package com.bawei6.usermodule.bean;

public class ChatBean {


    String type;
    int flag;
    String msg;


    public ChatBean(String type, int flag, String msg) {
        this.type = type;
        this.flag = flag;
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
