package com.bawei6.basemodule.basemvp;

import com.bawei6.basemodule.bean.AddFriendsBean;
import com.bawei6.basemodule.bean.AddGroupBean;
import com.bawei6.basemodule.bean.CreateGroupBean;
import com.bawei6.basemodule.bean.CreateGroupBodyBean;
import com.bawei6.basemodule.bean.FindGroupBean;
import com.bawei6.basemodule.bean.FindGroupNameBean;
import com.bawei6.basemodule.bean.GetMyGroupBean;
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
import com.bawei6.basemodule.utils.LogUtils;

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
                LogUtils.e("注册"+e.getMessage());
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
                LogUtils.e("登陆"+e.getMessage());
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
                LogUtils.e("修改密码"+e.getMessage());
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
                LogUtils.i("搜索好友"+e.getMessage());
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
                LogUtils.i("loadAddFri"+e.getMessage());
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
                LogUtils.i("loadUserFirip"+e.getMessage());
            }
        },view.getowner());
    }

    @Override
    public void loadGroupBeanP(CreateGroupBodyBean createGroupBodyBean) {
        Observable<CreateGroupBean> createGroupBeanObservable = model.loadGroupBeanM(createGroupBodyBean);
        BaseObserable.doObserver(createGroupBeanObservable,new BaseObserver<CreateGroupBean>(){
            @Override
            public void onNext(CreateGroupBean createGroupBean) {
                super.onNext(createGroupBean);
                view.showGrouption(createGroupBean.isData());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
               LogUtils.i("创建建群"+e.getMessage());
            }
        },view.getowner());
    }

    @Override
    public void loadFindGroup(String groupId) {
        Observable<FindGroupBean> findGroupBeanObservable = model.loadFindGroupM(groupId);
        BaseObserable.doObserver(findGroupBeanObservable,new BaseObserver<FindGroupBean>(){
            @Override
            public void onNext(FindGroupBean findGroupBean) {
                super.onNext(findGroupBean);

                    FindGroupBean.DataBean data = findGroupBean.getData();
                    if(data!=null){
                        List<FindGroupBean.DataBean> list=new ArrayList<>();
                        list.add(data);
                        view.showFindGroup(list);
                    }


            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LogUtils.i("findGrouop---->"+e.getMessage());
            }
        },view.getowner());
    }

    @Override
    public void loadAddGroupP(String grouopId, String userCode) {
        Observable<AddGroupBean> addGroupBeanObservable = model.loadAddGroupM(grouopId, userCode);
        BaseObserable.doObserver(addGroupBeanObservable,new BaseObserver<AddGroupBean>(){
            @Override
            public void onNext(AddGroupBean addGroupBean) {
                super.onNext(addGroupBean);
                view.showAddGroupResult(addGroupBean.isData());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LogUtils.i(e.getMessage());
            }
        },view.getowner());
    }

    @Override
    public void loadFindGroupName(String key) {
        Observable<FindGroupNameBean> findGroupNameBeanObservable = model.loadFindGroupName(key);
        BaseObserable.doObserver(findGroupNameBeanObservable,new BaseObserver<FindGroupNameBean>(){
            @Override
            public void onNext(FindGroupNameBean findGroupNameBean) {
                super.onNext(findGroupNameBean);
                List<FindGroupNameBean.DataBean> data = findGroupNameBean.getData();
                view.showFindGroupName(data);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        },view.getowner());
    }

    @Override
    public void loadgetMyGroupP(String usercode) {
        Observable<GetMyGroupBean> getMyGroupBeanObservable = model.loadMyGroupBeanM(usercode);
        BaseObserable.doObserver(getMyGroupBeanObservable,new BaseObserver<GetMyGroupBean>(){
            @Override
            public void onNext(GetMyGroupBean getMyGroupBean) {
                super.onNext(getMyGroupBean);
                List<GetMyGroupBean.DataBean> data = getMyGroupBean.getData();
                view.showMyGroup(data);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LogUtils.i(e.getMessage());
            }
        },view.getowner());
    }


}
