package com.bawei6.immodule.entity;

import com.google.gson.Gson;

public class LocationMsg extends BaseMsg {
    private Double lon;
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public String getMsg() {
        return new Gson().toJson(this);
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.LOCATION;
    }
}
