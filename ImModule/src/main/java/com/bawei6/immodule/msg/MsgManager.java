package com.bawei6.immodule.msg;

import com.bawei6.immodule.entity.BaseMsg;

public class MsgManager {

    private static volatile MsgManager instance;

    private MsgManager() {}

    public static MsgManager getInstance(){
        if(instance==null){
            synchronized (MsgManager.class){
                if(instance==null){
                    instance=new MsgManager();
                }
            }
        }
        return instance;
    }

    private IMsg msg;

    public void init(IMsg _msg){
        msg=_msg;
    }

    public void sendMsg(BaseMsg msg){
        this.msg.sendMsg(msg);
    }

    public void receiveMsg(){
        this.msg.receiveMsg();
    }
}

