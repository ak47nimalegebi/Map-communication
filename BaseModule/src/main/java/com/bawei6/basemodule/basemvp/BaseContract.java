package com.bawei6.basemodule.basemvp;

import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.LogBodyBean;
import com.bawei6.basemodule.bean.ResBean;
import com.bawei6.basemodule.bean.ResBodyBean;
import com.bawei6.basemodule.bean.UpdateBean;
import com.bawei6.basemodule.bean.UpdateBodyBean;

import io.reactivex.Observable;

public interface BaseContract {

    interface BaseModel extends IModel{
        Observable<ResBean> LoadResBeanM(String name, String pwd, ResBodyBean resBodyBean);
        Observable<LogBean> LoadLogBeanM(String name, String pwd, LogBodyBean logBodyBean);
        Observable<UpdateBean> LoadUpdataBeanM(String id, String pwd, UpdateBodyBean updateBodyBean);
    }

    interface BaseView extends IView{
        void showResult(String msg);
        void showLogResult(String logResult);
        void showUpdateResult(String updateResult);
    }


    abstract class Presenter extends BasePresenter<BaseView,BaseModel>{
        abstract public void LoadResBeanP(String name,String pwd,ResBodyBean resBodyBean);
        abstract public void LoadLogBeanP(String name,String pwd,LogBodyBean logBodyBean);
        abstract public void LoadUpdateBeanP(String id,String pwd,UpdateBodyBean updateBodyBean);
    }
}
