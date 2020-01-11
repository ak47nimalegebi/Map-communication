package com.bawei6.immodule.entity;

import com.google.gson.Gson;

public class ImgMsg extends BaseMsg {
    private String imgPath;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.IMG;
    }

    @Override
    public String getMsg() {
        return new Gson().toJson(this);
    }
}
