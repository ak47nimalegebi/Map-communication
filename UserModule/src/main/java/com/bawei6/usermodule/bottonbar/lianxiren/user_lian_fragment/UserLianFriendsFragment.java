package com.bawei6.usermodule.bottonbar.lianxiren.user_lian_fragment;

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
import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.ScoureBean;
import com.bawei6.basemodule.bean.UserFriBean;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.RecyUserLianAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserLianFriendsFragment extends Fragment implements BaseContract.BaseView {

    private RecyclerView recyclerView;
    private List<UserFriBean.DataBean> list=new ArrayList<>();
    private RecyUserLianAdapter recyUserLianAdapter;
    private Presenter presenter=new Presenter();
    private String userCode;


    public UserLianFriendsFragment(String code) {
        this.userCode=code;
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
    public void showUserFri(List<UserFriBean.DataBean> list) {

        recyUserLianAdapter=new RecyUserLianAdapter(getContext(),list);
        recyclerView.setAdapter(recyUserLianAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyUserLianAdapter.notifyDataSetChanged();
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
