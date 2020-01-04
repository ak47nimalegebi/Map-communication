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

import java.util.List;

import io.reactivex.Observable;

public interface BaseContract {

    interface BaseModel extends IModel{
        Observable<ResBean> LoadResBeanM(String name, String pwd, ResBodyBean resBodyBean);
        Observable<LogBean> LoadLogBeanM(String name, String pwd, LogBodyBean logBodyBean);
        Observable<UpdateBean> LoadUpdataBeanM(String id, String pwd, UpdateBodyBean updateBodyBean);
        Observable<ScoureBean> LoadScourBeanM(String userName);
        Observable<AddFriendsBean> loadAddFraM(String userCode,String friCode);
        Observable<UserFriBean> loadUserFriM(String usercode);
    }

    interface BaseView extends IView{
        void showResult(String msg);
        void showLogResult(List<LogBean.DataBean> list,String logResult,int code);
        void showUpdateResult(String updateResult);
        void showScourBean(List<ScoureBean.DataBean> list);
        void showAddFriResult(String msg,Boolean flag);
        void showUserFri(List<UserFriBean.DataBean> list);
    }


    abstract class Presenter extends BasePresenter<BaseView,BaseModel> {
        abstract public void LoadResBeanP(String name,String pwd,ResBodyBean resBodyBean);
        abstract public void LoadLogBeanP(String name,String pwd,LogBodyBean logBodyBean);
        abstract public void LoadUpdateBeanP(String id,String pwd,UpdateBodyBean updateBodyBean);
        abstract public void LoadSourBeanP(String userName);
        abstract public void loadAddFriP(String userCode,String friCode);
        abstract public void loadUserFriP(String usercode);
    }
}
