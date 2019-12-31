package com.bawei6.basemodule.basemvp;

import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.LogBodyBean;
import com.bawei6.basemodule.bean.ResBean;
import com.bawei6.basemodule.bean.ResBodyBean;
import com.bawei6.basemodule.bean.UpdateBean;
import com.bawei6.basemodule.bean.UpdateBodyBean;
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
    public void Destory() {

    }
}
