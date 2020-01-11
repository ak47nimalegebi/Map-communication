package com.bawei6.usermodule.bottonbar.lianxiren.user_lian_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.basemvp.BaseContract;
import com.bawei6.basemodule.basemvp.Presenter;
import com.bawei6.basemodule.bean.FindGroupBean;
import com.bawei6.basemodule.bean.FindGroupNameBean;
import com.bawei6.basemodule.bean.GetMyGroupBean;
import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.ScoureBean;
import com.bawei6.basemodule.bean.UserFriBean;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.RecyMyGroupAdapter;

import java.util.List;

public class UserLianGroupFragment extends Fragment implements BaseContract.BaseView {


    TextView textView;
    private String userCode;
    private RecyclerView recyclerView;
    private Presenter presenter=new Presenter();
    private RecyMyGroupAdapter recyMyGroupAdapter;

    public UserLianGroupFragment(String userfragcode) {
            this.userCode=userfragcode;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.user_lian_group, null);
        recyclerView=inflate.findViewById(R.id.mygroup_recy);
        presenter.setAttachVie(this);
        presenter.loadgetMyGroupP(userCode);
        return inflate;
    }

    @Override
    public void showResult(String msg) {

    }

    @Override
    public void showLogResult(List<LogBean.DataBean> list, String logResult, int code) {

    }

    @Override
    public void showUpdateResult(String updateResult) {

    }

    @Override
    public void showScourBean(List<ScoureBean.DataBean> list) {

    }

    @Override
    public void showAddFriResult(String msg, Boolean flag) {

    }

    @Override
    public void showUserFri(List<UserFriBean.DataBean> list) {

    }

    @Override
    public void showGrouption(Boolean flag) {

    }

    @Override
    public void showFindGroup(List<FindGroupBean.DataBean> list) {

    }

    @Override
    public void showAddGroupResult(Boolean flag) {

    }

    @Override
    public void showFindGroupName(List<FindGroupNameBean.DataBean> list) {

    }

    @Override
    public void showMyGroup(List<GetMyGroupBean.DataBean> list) {
        recyMyGroupAdapter=new RecyMyGroupAdapter(getContext(),list);
        recyclerView.setAdapter(recyMyGroupAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public LifecycleOwner getowner() {
        return this;
    }
}
