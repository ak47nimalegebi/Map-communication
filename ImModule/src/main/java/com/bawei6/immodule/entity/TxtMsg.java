package com.bawei6.immodule.entity;

import com.google.gson.Gson;

public class TxtMsg extends BaseMsg {
    private String txt;

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TXT;
    }

    @Override
    public String getMsg() {
        return new Gson().toJson(this);
    }
}
