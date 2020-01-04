package com.bawei6.usermodule.bottonbar.daun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.basemvp.BaseContract;
import com.bawei6.basemodule.basemvp.Presenter;
import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.ScoureBean;
import com.bawei6.basemodule.bean.UserFriBean;
import com.bawei6.usermodule.CallBackOnclick;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.RecyXinAdapter;

import java.util.List;

public class DuanxinActivity extends AppCompatActivity implements BaseContract.BaseView {

    private RecyclerView recyclerView;
    private Presenter presenter=new Presenter();
    private RecyXinAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duanxin);

        recyclerView=findViewById(R.id.xin_recy);
        presenter.setAttachVie(this);
        presenter.loadUserFriP("4cd6bba4e59d47bdb94a59d69e04b69c");



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
        adapter=new RecyXinAdapter(this,list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.getXinOnclick(new CallBackOnclick() {
            @Override
            public void getOnclick(View view, int position) {
                startActivity(new Intent());
            }
        });

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
