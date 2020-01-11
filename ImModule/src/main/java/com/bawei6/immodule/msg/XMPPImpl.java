package com.bawei6.immodule.msg;

import android.os.Looper;

import com.bawei6.common.LogUtils;
import com.bawei6.immodule.entity.BaseMsg;
import com.bawei6.immodule.entity.MsgType;
import com.bawei6.immodule.exception.ReceiveException;
import com.bawei6.immodule.notify.NotifyManager;
import com.bawei6.immodule.task.TaskManager;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.entity.MsgEntity;

public class XMPPImpl implements IMsg {

    private XMPPImpl(){

    }

    private static XMPPImpl instance = new XMPPImpl();

    public static XMPPImpl getInstance(){
        return instance;
    }
    @Override
    public void sendMsg(final BaseMsg msg) {

        synchronized (XMPPImpl.class){
            if(Looper.getMainLooper().getThread()==Thread.currentThread()){
                TaskManager.getInstance().doTask(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.i("发送消息");
                        XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(msg.getTo(),msg.getMsg());
                    }
                });
            }else {
                LogUtils.i("发送消息");
                XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(msg.getTo(),msg.getMsg());
            }
        }

    }

    @Override
    public void receiveMsg() {
        XmppManager.getInstance().addMessageListener(new IMsgCallback() {
            @Override
            public void Success(MsgEntity msgEntity) {
                LogUtils.i("开始服务接收");
                BaseMsg msg=convertMsgForMsgEntity(msgEntity);
                NotifyManager.getInstance().notifyAllObserver(msg);
            }

            @Override
            public void Failed(Throwable throwable) {
                try {
                    throw new ReceiveException(throwable.getMessage());
                } catch (ReceiveException e) {
                    e.printStackTrace();
                    LogUtils.i(e.getMessage());
                }
            }
        });
    }

    private BaseMsg convertMsgForMsgEntity(MsgEntity msgEntity) {
        MsgEntity.MsgType msgType = msgEntity.getMsgType();
        BaseMsg msg = new BaseMsg(msgEntity.getFrom(), msgEntity.getTo(), msgEntity.getMsg(), convertTypeForMsgEntity(msgType));
        return msg;
    }

    private MsgType convertTypeForMsgEntity(MsgEntity.MsgType msgType) {
        switch (msgType){
            case Img:
                return MsgType.IMG;
            case Txt:
                return MsgType.TXT;
            case Audio:
                return MsgType.AUDIO;
            case Video:
                return MsgType.VIDEO;
            case Location:
                return MsgType.LOCATION;
            case ICON:
                return MsgType.ICON;
        }
        return MsgType.OTHER;
    }
}
