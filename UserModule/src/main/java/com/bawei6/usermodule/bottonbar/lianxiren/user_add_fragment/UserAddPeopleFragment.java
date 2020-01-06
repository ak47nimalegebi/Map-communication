package com.bawei6.usermodule.bottonbar.lianxiren.user_add_fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.bawei6.usermodule.CallBackOnclick;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.RecySocureAdapter;
import com.bawei6.usermodule.adapter.RecyUserLianxiPhoneAdapter;
import com.bawei6.usermodule.bottonbar.lianxiren.AddLianxirenActivity;
import com.baweigame.xmpplibrary.XmppManager;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.List;

public class UserAddPeopleFragment extends Fragment implements BaseContract.BaseView {

    private EditText edi_userName;
    private RecyclerView recyclerView;
    private Presenter presenter=new Presenter();
    private RecySocureAdapter recySocureAdapter;
    private Button btn_scour;
    private TextView tv_lianxiren,tv_mian;
    private String userCode;
    private RecyUserLianxiPhoneAdapter recyUserLianxiPhoneAdapter;

    public UserAddPeopleFragment(String mycode) {
        this.userCode=mycode;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.addfriends_people, null);
        edi_userName=inflate.findViewById(R.id.addfra_edi);
        recyclerView=inflate.findViewById(R.id.addfra_find_recy);
        btn_scour=inflate.findViewById(R.id.addfri_btn);
        tv_lianxiren=inflate.findViewById(R.id.addfra_tv_lian);
        tv_mian=inflate.findViewById(R.id.addfra_tv_mian);

        presenter.setAttachVie(this);

        tv_lianxiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddLianxirenActivity.class));
            }
        });

        tv_mian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent,1111);
            }
        });

        btn_scour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edi_userName.getText().toString();
                presenter.LoadSourBeanP(userName);
            }
        });
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
    public void showScourBean(final List<ScoureBean.DataBean> list) {
        recySocureAdapter=new RecySocureAdapter(getContext(),list);
        recyclerView.setAdapter(recySocureAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        recySocureAdapter.getonClick(new CallBackOnclick() {
            @Override
            public void getOnclick(View view, int position) {
                String friCode = list.get(position).getUsercode();
                String username = list.get(position).getUsername();
                presenter.loadAddFriP(userCode,friCode);
                boolean b = XmppManager.getInstance().getXmppFriendManager().addFriend(username, username);
                if(b==true){
                    Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
                }
                Log.i("FriCode","user"+userCode+"\n"+friCode);

            }
        });



    }

    @Override
    public void showAddFriResult(String msg, Boolean flag) {

    }

    @Override
    public void showUserFri(List<UserFriBean.DataBean> list) {

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1111&&resultCode== Activity.RESULT_OK)
        if(data!=null){

        }
    }


}
