package com.bawei6.usermodule.bottonbar.lianxiren.user_add_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bawei6.basemodule.basemvp.BaseContract;
import com.bawei6.basemodule.basemvp.Presenter;
import com.bawei6.basemodule.bean.FindGroupBean;
import com.bawei6.basemodule.bean.FindGroupNameBean;
import com.bawei6.basemodule.bean.GetMyGroupBean;
import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.ScoureBean;
import com.bawei6.basemodule.bean.UserFriBean;
import com.bawei6.usermodule.CallBackOnclick;
import com.bawei6.usermodule.NewGroupActivity;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.RecyNewGroupFindAdapter;
import com.bawei6.usermodule.adapter.UserLianPagerAdapter;
import com.baweigame.xmpplibrary.XmppManager;
import com.google.android.material.tabs.TabLayout;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatException;
import org.jxmpp.jid.parts.Resourcepart;
import org.jxmpp.stringprep.XmppStringprepException;

import java.util.ArrayList;
import java.util.List;

public class UserAddGroupFragment extends Fragment implements BaseContract.BaseView {

    private EditText edi_userName;
    private Button btn_find;
    private TextView tv_creategroup,tv_sao;
    private TabLayout tabLayout;
    private ViewPager pager;
    private Presenter presenter=new Presenter();

    private FragmentManager manager;
    private String code;
    private String name;

    private List<Fragment> fragmentList=new ArrayList<>();
    private List<String> titleList=new ArrayList<>();
    private UserLianPagerAdapter adapter;

    private Fragment gameFra,fenFra,jiaoFra;
    private String jid="@api.city2sky";




    private RecyclerView recyclerView;
    private RecyNewGroupFindAdapter recyNewGroupFindAdapter;

    private Handler handler=new Handler();

    public UserAddGroupFragment(FragmentManager manager, String liancode, String lianname) {
        this.manager=manager;
        this.code=liancode;
        this.name=lianname;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.addfriends_group, null);

        edi_userName=inflate.findViewById(R.id.addgroup_edi);
        btn_find=inflate.findViewById(R.id.addgroup_btn);
        tv_creategroup=inflate.findViewById(R.id.addgroup_tv_create);
        tv_sao=inflate.findViewById(R.id.addgroup_tv_sao);
        recyclerView=inflate.findViewById(R.id.newgroup_find_group_recy);

        Log.i("JOJO",name);


//        tabLayout=inflate.findViewById(R.id.addgroup_tab);
//        pager=inflate.findViewById(R.id.addgroup_pager);

        presenter.setAttachVie(this);



//        gameFra=new AddGroupGameFragment();
//        fenFra=new AddGroupFensiFragment();
//        jiaoFra=new AddGroupJiaoFragment();
//
//        fragmentList.add(gameFra);
//        fragmentList.add(fenFra);
//        fragmentList.add(jiaoFra);
//
//        titleList.add("游戏");
//        titleList.add("粉丝");
//        titleList.add("交友");

//        adapter=new UserLianPagerAdapter(manager,fragmentList,titleList);
//
//        pager.setAdapter(adapter);
//
//        tabLayout.setupWithViewPager(pager);

        tv_creategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), NewGroupActivity.class));
            }
        });

        //搜群
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groupId = edi_userName.getText().toString();
                if(groupId.isEmpty()){
                    Toast.makeText(getContext(), "请输入组号", Toast.LENGTH_SHORT).show();
                }
                presenter.loadFindGroupName(groupId);

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
        Toast.makeText(getContext(), ""+flag, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFindGroupName(final List<FindGroupNameBean.DataBean> list) {
        recyNewGroupFindAdapter=new RecyNewGroupFindAdapter(getContext(),list);
        recyclerView.setAdapter(recyNewGroupFindAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyNewGroupFindAdapter.getfindGroupOnclick(new CallBackOnclick() {
            @Override
            public void getOnclick(View view, int position) {
                int id = list.get(position).getId();
                String groupId = String.valueOf(id);
                String groupname = list.get(position).getGroupname();

                MultiUserChat multiUserChat = XmppManager.getInstance().getXmppChatRoomManager().joinMultiUserChat(name,groupname);
                Log.i("RRRR",name);
                try {
                    if(multiUserChat==null){
                        Toast.makeText(getContext(), "multiUserChat为空", Toast.LENGTH_SHORT).show();
                    }else {
                        multiUserChat.join(Resourcepart.from(name));
                    }

                } catch (SmackException.NoResponseException e) {
                    e.printStackTrace();
                } catch (XMPPException.XMPPErrorException e) {
                    e.printStackTrace();
                } catch (SmackException.NotConnectedException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (MultiUserChatException.NotAMucServiceException e) {
                    e.printStackTrace();
                } catch (XmppStringprepException e) {
                    e.printStackTrace();
                }
                presenter.loadAddGroupP(groupId,code);
            }
        });


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
