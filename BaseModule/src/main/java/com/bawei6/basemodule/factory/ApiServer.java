package com.bawei6.basemodule.factory;


import com.bawei6.basemodule.bean.TokenBean;

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

    @FormUrlEncoded
    @POST("token")
    Call<TokenBean> getTokenCall(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);


}
