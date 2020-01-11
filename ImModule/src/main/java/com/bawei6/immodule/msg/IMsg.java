package com.bawei6.immodule.msg;

import com.bawei6.immodule.entity.BaseMsg;

public interface IMsg{

    void sendMsg(BaseMsg msg);

    void receiveMsg();
}
