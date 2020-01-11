package com.bawei6.immodule.entity;

public class BaseMsg {

    private String from;
    private String to;
    private String msg;
    private MsgType msgType;

    public BaseMsg() {}

    public BaseMsg(String from, String to, String msg, MsgType msgType) {
        this.from = from;
        this.to = to;
        this.msg = msg;
        this.msgType = msgType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "BaseMsg{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", msg='" + msg + '\'' +
                ", msgType=" + msgType +
                '}';
    }
}
