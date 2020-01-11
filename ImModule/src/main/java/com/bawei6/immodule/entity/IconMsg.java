package com.bawei6.immodule.entity;

import com.google.gson.Gson;

public class IconMsg extends BaseMsg{
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String getMsg() {
        return new Gson().toJson(this);
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.ICON;
    }
}
