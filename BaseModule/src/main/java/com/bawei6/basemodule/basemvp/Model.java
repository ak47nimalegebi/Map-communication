package com.bawei6.basemodule.basemvp;

import com.bawei6.basemodule.bean.AddFriendsBean;
import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.LogBodyBean;
import com.bawei6.basemodule.bean.ResBean;
import com.bawei6.basemodule.bean.ResBodyBean;
import com.bawei6.basemodule.bean.ScoureBean;
import com.bawei6.basemodule.bean.UpdateBean;
import com.bawei6.basemodule.bean.UpdateBodyBean;
import com.bawei6.basemodule.bean.UserFriBean;
import com.bawei6.basemodule.factory.ApiServer;
import com.bawei6.basemodule.factory.RetrofitFactory;

import io.reactivex.Observable;

public class Model implements BaseContract.BaseModel {
    @Override
    public Observable<ResBean> LoadResBeanM(String name, String pwd, ResBodyBean resBodyBean) {
        ApiServer apiServer = RetrofitFactory.getInstance().create(ApiServer.class);
        Observable<ResBean> resBean = apiServer.getResBean(resBodyBean);
        return resBean;
    }

    @Override
    public Observable<LogBean> LoadLogBeanM(String name, String pwd, LogBodyBean logBodyBean) {
        ApiServer apiServer = RetrofitFactory.getInstance().create(ApiServer.class);
        Observable<LogBean> logBean = apiServer.getLogBean(logBodyBean);
        return logBean;
    }

    @Override
    public Observable<UpdateBean> LoadUpdataBeanM(String id, String pwd, UpdateBodyBean updateBodyBean) {
        ApiServer apiServer = RetrofitFactory.getInstance().create(ApiServer.class);
        Observable<UpdateBean> updataBean = apiServer.getUpdataBean(updateBodyBean);
        return updataBean;
    }

    @Override
    public Observable<ScoureBean> LoadScourBeanM(String userName) {
        ApiServer apiServer = RetrofitFactory.getInstance().create(ApiServer.class);
        Observable<ScoureBean> scourBean = apiServer.getScourBean(userName, "");
        return scourBean;
    }

    @Override
    public Observable<AddFriendsBean> loadAddFraM(String userCode, String friCode) {
        ApiServer apiServer = RetrofitFactory.getInstance().create(ApiServer.class);
        Observable<AddFriendsBean> addFreBean = apiServer.getAddFreBean(userCode, friCode);
        return addFreBean;
    }

    @Override
    public Observable<UserFriBean> loadUserFriM(String usercode) {
        ApiServer apiServer = RetrofitFactory.getInstance().create(ApiServer.class);
        Observable<UserFriBean> userFriBean = apiServer.getUserFriBean(usercode);
        return userFriBean;
    }

    @Override
    public void Destory() {

    }
}
