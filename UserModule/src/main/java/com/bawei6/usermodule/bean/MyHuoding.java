package com.bawei6.usermodule.bean;

public class MyHuoding {

    String time;
    String num;
    String title;

    public MyHuoding(String time, String num, String title) {
        this.time = time;
        this.num = num;
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
