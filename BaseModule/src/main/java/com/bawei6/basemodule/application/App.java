package com.bawei6.basemodule.application;

import android.app.Application;

import com.bawei6.basemodule.basemvp.DeviceInfoConfig.AppInfoConfig;
import com.bawei6.basemodule.basemvp.DeviceInfoConfig.DeviceInfoConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DeviceInfoConfig.getInstance().init(this);
        AppInfoConfig.getInstance().init(this);
    }
}
