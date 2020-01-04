package com.bawei6.basemodule.DeviceInfoConfig;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import com.bawei6.basemodule.utils.LogUtils;
import com.umeng.commonsdk.utils.UMUtils;

public class DeviceInfoConfig {

    private Context context;

    private static volatile DeviceInfoConfig singleton;

    private DeviceInfoConfig() {
    }

    public static DeviceInfoConfig getInstance() {
        if (singleton == null) {
            synchronized (DeviceInfoConfig.class) {
                if (singleton == null) {
                    singleton = new DeviceInfoConfig();
                }
            }
        }
        return singleton;
    }


    public void init(Context context){
        this.context=context;
    }

    public String getMANUFACTURER(){
        return Build.MANUFACTURER;
    }
    public String getModel(){
        return Build.MODEL;
    }

    public String getOsVersion(){
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    //设备号 GSM-IMEI CDMA-MEID
    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getDeviceID(){
        if(Build.VERSION.SDK_INT<23){
            LogUtils.d("os version < 23");
        }else {
            LogUtils.d("os version > 23");
            String deiceid=getIMEI();
            if(TextUtils.isEmpty(deiceid)){
                deiceid=getMEID();
            }
            return deiceid;
        }
        return null;
    }

    public String getUtdid(){

        return UMUtils.getUTDID(context);
    }

    public String getMacAddress(){
        return UMUtils.getMac(context);
    }

    public String getDisplay(){
        return UMUtils.getDisplayResolution(context);
    }

    //获取位置
    @SuppressLint("MissingPermission")
    public String getLocation(){
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        return String.valueOf(lastKnownLocation.getLatitude())+String.valueOf(lastKnownLocation.getLongitude());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    private String getMEID() {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
         String meid = telephonyManager.getDeviceId(TelephonyManager.PHONE_TYPE_CDMA);
        return meid;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    private String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(TelephonyManager.PHONE_TYPE_GSM);
        return imei;
    }
}