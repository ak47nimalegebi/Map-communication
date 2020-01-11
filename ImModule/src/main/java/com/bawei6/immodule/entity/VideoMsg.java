package com.bawei6.immodule.entity;

import com.google.gson.Gson;

public class VideoMsg extends BaseMsg {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getMsg() {
        return new Gson().toJson(this);
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.VIDEO;
    }
}
