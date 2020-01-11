package com.bawei6.immodule;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.bawei6.common.LogUtils;
import com.bawei6.immodule.msg.MsgManager;
import com.bawei6.immodule.msg.XMPPImpl;

public class ListenerService extends Service {

    public ListenerService() {
        LogUtils.i("服务被启动");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.i("create----->开始监听");
        MsgManager.getInstance().init(XMPPImpl.getInstance());
        MsgManager.getInstance().receiveMsg();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
