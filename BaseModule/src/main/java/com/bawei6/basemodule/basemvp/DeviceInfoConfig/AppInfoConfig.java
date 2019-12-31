package com.bawei6.basemodule.basemvp.DeviceInfoConfig;

import android.content.Context;
import android.content.pm.PackageManager;

public class AppInfoConfig {

    private Context context;

    private static volatile AppInfoConfig singleton;

    private AppInfoConfig() {
    }

    public static AppInfoConfig getInstance() {
        if (singleton == null) {
            synchronized (AppInfoConfig.class) {
                if (singleton == null) {
                    singleton = new AppInfoConfig();
                }
            }
        }
        return singleton;
    }

    public void init(Context context){
        this.context=context;
    }

    public String getPackageManager(){
        PackageManager packageManager=context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(),0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getVersionname(){
        PackageManager packageManager=context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getVersionCode(){
        PackageManager packageManager=context.getPackageManager();
        try {
            return String.valueOf(packageManager.getPackageInfo(context.getPackageName(),0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}