package com.bawei6.usermodule.bottonbar.lianxiren.user_lian_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.bawei6.usermodule.CallBackOnclick;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.RecyUserLianAdapter;
import com.bawei6.usermodule.bottonbar.daun.ChatActivity;

import java.util.List;

public class UserLianFriendsFragment extends Fragment implements BaseContract.BaseView {

    private RecyclerView recyclerView;
    private RecyUserLianAdapter recyUserLianAdapter;
    private Presenter presenter=new Presenter();
    private String userCode;
    private String userName;


    public UserLianFriendsFragment(String myName, String code) {
        this.userCode=code;
        this.userName=myName;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.user_lian_friends, null);
        recyclerView=inflate.findViewById(R.id.user_friend_recy);

        Log.i("pressss",userCode);
        presenter.setAttachVie(this);
        presenter.loadUserFriP(userCode);

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
    public void showUserFri(final List<UserFriBean.DataBean> list) {

        recyUserLianAdapter=new RecyUserLianAdapter(getContext(),list);
        recyclerView.setAdapter(recyUserLianAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyUserLianAdapter.notifyDataSetChanged();


        recyUserLianAdapter.getClick(new CallBackOnclick() {
            @Override
            public void getOnclick(View view, int position) {
                String friCode = list.get(position).getUsercode();
                String username = list.get(position).getUsername();
                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra("userlianfralogcode",userCode);
                intent.putExtra("userlianfralogname",userName);
                intent.putExtra("userlianfrafricode",friCode);
                intent.putExtra("userlianfrafriname",username);
                startActivity(intent);
            }
        });
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
