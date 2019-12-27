package com.bawei6.basemodule.factory;

import com.bawei6.basemodule.bean.BannerBean;
import com.bawei6.basemodule.bean.ChanBean;
import com.bawei6.basemodule.bean.HomeBean;
import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.LogBodyBean;
import com.bawei6.basemodule.bean.PingBean;
import com.bawei6.basemodule.bean.PingBodyBean;
import com.bawei6.basemodule.bean.PingLunBean;
import com.bawei6.basemodule.bean.ResBean;
import com.bawei6.basemodule.bean.ResBodyBean;
import com.bawei6.basemodule.bean.TokenBean;
import com.bawei6.basemodule.bean.UpdateBean;
import com.bawei6.basemodule.bean.UpdateBodyBean;
import com.bawei6.basemodule.bean.YanBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {

    @FormUrlEncoded
    @POST("token")
    Observable<TokenBean>getToken(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

    @GET("api/User/getUserInfo/")
    Observable<MyBean>getMyBean(@Query("id") String id);

    @GET("api/User/getAuthCode?phoneNumber=")
    Observable<YanBean> getYanBean(@Query("phoneNumber") String phoneNumber);


    @POST("api/User/register")
    Observable<ResBean>getResBean(@Body ResBodyBean resBodyBean);

    @POST("api/User/login")
    Observable<LogBean> getLogBean(@Body LogBodyBean logBodyBean);


    @POST("api/Comment/publishComment")
    Observable<PingBean> getPingBean(@Body PingBodyBean pingBodyBean);

    @POST("api/User/modifyPwd")
    Observable<UpdateBean> getUpdateBean(@Body UpdateBodyBean updateBodyBean);

    @GET("api/Product/getProcducts")
    Observable<HomeBean> getHomeBean();

    @GET("api/Img/getBannerImg")
    Observable<ArrayList<BannerBean>> getBannnerBeann();

    @GET("api/Product/getNewUserProcducts")
    Observable<ChanBean> getChanBean();


    @FormUrlEncoded
    @POST("token")
    Call<TokenBean> getTokenCall(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);


    @GET("api/Comment/getCommentByProcductid?")
    Observable<ArrayList<PingLunBean>> getPingLunBean(@Query("procductid") String procductid);

}
