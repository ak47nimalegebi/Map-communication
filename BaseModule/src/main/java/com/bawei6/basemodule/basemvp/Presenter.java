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
import com.bawei6.basemodule.utils.BaseObserable;
import com.bawei6.basemodule.utils.BaseObserver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class Presenter extends BaseContract.Presenter {

    private BaseContract.BaseView view;
    private Model model=new Model();

    public void setAttachVie(BaseContract.BaseView view){
        this.view=view;
    }

    @Override
    public void LoadResBeanP(String name, String pwd, ResBodyBean resBodyBean) {
        Observable<ResBean> resBeanObservable = model.LoadResBeanM(name, pwd, resBodyBean);
        BaseObserable.doObserver(resBeanObservable,new BaseObserver<ResBean>(){
            @Override
            public void onNext(ResBean resBean) {
                super.onNext(resBean);
                view.showResult(resBean.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        },view.getowner());
    }

    @Override
    public void LoadLogBeanP(String name, String pwd, LogBodyBean logBodyBean) {
        Observable<LogBean> logBeanObservable = model.LoadLogBeanM(name, pwd, logBodyBean);
        BaseObserable.doObserver(logBeanObservable,new BaseObserver<LogBean>(){
            @Override
            public void onNext(LogBean logBean) {
                super.onNext(logBean);
                LogBean.DataBean data = logBean.getData();
                List<LogBean.DataBean> list=new ArrayList<>();
                list.add(data);
                view.showLogResult(list,logBean.getMsg(),logBean.getCode());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        },view.getowner());
    }


    @Override
    public void LoadUpdateBeanP(String id, String pwd, UpdateBodyBean updateBodyBean) {
        Observable<UpdateBean> updateBeanObservable = model.LoadUpdataBeanM(id, pwd, updateBodyBean);
        BaseObserable.doObserver(updateBeanObservable,new BaseObserver<UpdateBean>(){
            @Override
            public void onNext(UpdateBean updateBean) {
                super.onNext(updateBean);
                view.showUpdateResult(updateBean.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        },view.getowner());
    }

    @Override
    public void LoadSourBeanP(String userName) {
        Observable<ScoureBean> scoureBeanObservable = model.LoadScourBeanM(userName);
        BaseObserable.doObserver(scoureBeanObservable,new BaseObserver<ScoureBean>(){
            @Override
            public void onNext(ScoureBean scoureBean) {
                super.onNext(scoureBean);
                List<ScoureBean.DataBean> data = scoureBean.getData();
                view.showScourBean(data);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        },view.getowner());
    }

    @Override
    public void loadAddFriP(String userCode, String friCode) {
        Observable<AddFriendsBean> addFriendsBeanObservable = model.loadAddFraM(userCode, friCode);
        BaseObserable.doObserver(addFriendsBeanObservable,new BaseObserver<AddFriendsBean>(){
            @Override
            public void onNext(AddFriendsBean addFriendsBean) {
                super.onNext(addFriendsBean);

                view.showAddFriResult(addFriendsBean.getMsg(),addFriendsBean.isData());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        },view.getowner());
    }

    @Override
    public void loadUserFriP(final String usercode) {
        Observable<UserFriBean> userFriBeanObservable = model.loadUserFriM(usercode);
        BaseObserable.doObserver(userFriBeanObservable,new BaseObserver<UserFriBean>(){
            @Override
            public void onNext(UserFriBean userFriBean) {
                super.onNext(userFriBean);
                List<UserFriBean.DataBean> data = userFriBean.getData();
                view.showUserFri(data);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        },view.getowner());
    }


}
