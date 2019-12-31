package com.bawei6.basemodule.factory;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitFactory {

    private static volatile RetrofitFactory singleton;
    private static OkHttpClient.Builder builder;
    private static Retrofit retrofit;

    private RetrofitFactory() {
       retrofit  = new Retrofit.Builder()
                .client(initClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.zydeveloper.com:10001/")
                .build();
    }

    public static RetrofitFactory getInstance() {
        if (singleton == null) {
            synchronized (RetrofitFactory.class) {
                if (singleton == null) {
                    singleton = new RetrofitFactory();
                }
            }
        }
        return singleton;
    }

    private Interceptor initLogInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }


    private Interceptor createRequestInterceptor() {

        Interceptor interceptor = new Interceptor() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

//              如果是401 重新同步请求Token然后加载到新请求的Hander里
                if (checkHttpCode401(response)) {
                    String token = requestToken();
                    Request newRequest = request
                            .newBuilder()
                            .addHeader("Content-Type", "application-json")
                            .addHeader("charset", "utf-8")
                            .addHeader("Authorization", "bearer " + token)
//                            .addHeader("manufacturer", DeviceInfoConfig.getInstance().getMANUFACTURER())
//                            .addHeader("model",DeviceInfoConfig.getInstance().getModel())
//                            .addHeader("deviceid",DeviceInfoConfig.getInstance().getDeviceID())
//                            .addHeader("utdid",DeviceInfoConfig.getInstance().getUtdid())
//                            .addHeader("packagename", AppInfoConfig.getInstance().getPackageManager())
//                            .addHeader("versoincode", AppInfoConfig.getInstance().getVersionCode())
//                            .addHeader("versionname",AppInfoConfig.getInstance().getVersionname())
//                            .addHeader("location",DeviceInfoConfig.getInstance().getLocation())
//                            .addHeader("macaddress",DeviceInfoConfig.getInstance().getMacAddress())
//                            .addHeader("display",DeviceInfoConfig.getInstance().getDisplay())
//                            .addHeader("osverion", DeviceInfoConfig.getInstance().getOsVersion())
                            .build();
                    return chain.proceed(newRequest);
                }
                return response;
            }
        };
        return interceptor;
    }

    private String requestToken() {
        String password = null;
        try {
            ApiServer apiServer = RetrofitFactory.getInstance().create(ApiServer.class);
             password = apiServer.getTokenCall("password", "a31ba1ac10113b10e1121c619310b1b619a19c1f116c1e51", "")
                    .execute().body().getAccess_token();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }

    private boolean checkHttpCode401(Response response) {
        if (response == null){
            return false;
        }
        if (response.code() == 401){
            return true;
        }else {
            return false;
        }
    }



    public OkHttpClient initClient() {
        return getOkHttpClient()
//                .addInterceptor(addTokenHander())
                .addInterceptor(createRequestInterceptor())
                .addNetworkInterceptor(initLogInterceptor())
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
    }


    private OkHttpClient.Builder getOkHttpClient(){
        if (builder==null){
            builder = new OkHttpClient.Builder();
        }
        return builder;
    }

    public <T> T create(Class<T> tClass){
        return retrofit.create(tClass);
    }


}