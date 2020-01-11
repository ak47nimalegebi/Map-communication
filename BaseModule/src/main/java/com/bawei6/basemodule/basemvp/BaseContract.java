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
        Observable<CreateGroupBean> loadGroupBeanM(CreateGroupBodyBean createGroupBodyBean);
        Observable<FindGroupBean> loadFindGroupM(String groupId);
        Observable<AddGroupBean> loadAddGroupM(String groupId,String userCode);
        Observable<FindGroupNameBean> loadFindGroupName(String key);
        Observable<GetMyGroupBean> loadMyGroupBeanM(String usercode);
    }

    interface BaseView extends IView{
        void showResult(String msg);
        void showLogResult(List<LogBean.DataBean> list,String logResult,int code);
        void showUpdateResult(String updateResult);
        void showScourBean(List<ScoureBean.DataBean> list);
        void showAddFriResult(String msg,Boolean flag);
        void showUserFri(List<UserFriBean.DataBean> list);
        void showGrouption(Boolean flag);
        void showFindGroup(List<FindGroupBean.DataBean> list);
        void showAddGroupResult(Boolean flag);
        void showFindGroupName(List<FindGroupNameBean.DataBean> list);
        void showMyGroup(List<GetMyGroupBean.DataBean> list);
    }


    abstract class Presenter extends BasePresenter<BaseView,BaseModel> {
        abstract public void LoadResBeanP(String name,String pwd,ResBodyBean resBodyBean);
        abstract public void LoadLogBeanP(String name,String pwd,LogBodyBean logBodyBean);
        abstract public void LoadUpdateBeanP(String id,String pwd,UpdateBodyBean updateBodyBean);
        abstract public void LoadSourBeanP(String userName);
        abstract public void loadAddFriP(String userCode,String friCode);
        abstract public void loadUserFriP(String usercode);
        abstract public void loadGroupBeanP(CreateGroupBodyBean createGroupBodyBean);
        abstract public void loadFindGroup(String groupId);
        abstract public void loadAddGroupP(String grouopId,String userCode);
        abstract public void loadFindGroupName(String key);
        abstract public void loadgetMyGroupP(String usercode);
    }
}
