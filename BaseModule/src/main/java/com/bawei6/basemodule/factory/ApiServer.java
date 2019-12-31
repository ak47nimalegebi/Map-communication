package com.bawei6.basemodule.factory;


import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.LogBodyBean;
import com.bawei6.basemodule.bean.ResBean;
import com.bawei6.basemodule.bean.ResBodyBean;
import com.bawei6.basemodule.bean.TokenBean;
import com.bawei6.basemodule.bean.UpdateBean;
import com.bawei6.basemodule.bean.UpdateBodyBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServer {

    @FormUrlEncoded
    @POST("token")
    Observable<TokenBean>getToken(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

    @POST("api/User/register")
    Observable<ResBean> getResBean(@Body ResBodyBean resBodyBean);

    @POST("api/User/login")
    Observable<LogBean> getLogBean(@Body LogBodyBean logBodyBean);

    @FormUrlEncoded
    @POST("token")
    Call<TokenBean> getTokenCall(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

    @POST("api/User/modifyPwd")
    Observable<UpdateBean> getUpdataBean(@Body UpdateBodyBean updateBodyBean);

}
