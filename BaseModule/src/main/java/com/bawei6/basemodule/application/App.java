package com.bawei6.basemodule.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei6.basemodule.DeviceInfoConfig.AppInfoConfig;
import com.bawei6.basemodule.DeviceInfoConfig.DeviceInfoConfig;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DeviceInfoConfig.getInstance().init(this);
        AppInfoConfig.getInstance().init(this);
        ZXingLibrary.initDisplayOpinion(this);
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
