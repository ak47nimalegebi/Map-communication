package com.bawei6.usermodule.bean;

import android.net.Uri;

public class ChatBean {


    String type;
    int flag;
    String msg;
    Uri uri;


    public ChatBean(String type, int flag, String msg, Uri uri) {
        this.type = type;
        this.flag = flag;
        this.msg = msg;
        this.uri = uri;
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

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
