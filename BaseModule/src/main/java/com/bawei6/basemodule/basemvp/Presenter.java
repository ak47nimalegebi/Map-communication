package com.bawei6.basemodule.basemvp;

import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.LogBodyBean;
import com.bawei6.basemodule.bean.ResBean;
import com.bawei6.basemodule.bean.ResBodyBean;
import com.bawei6.basemodule.bean.UpdateBean;
import com.bawei6.basemodule.bean.UpdateBodyBean;
import com.bawei6.basemodule.utils.BaseObserable;
import com.bawei6.basemodule.utils.BaseObserver;

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
                view.showLogResult(logBean.getCode()+"");
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


}
