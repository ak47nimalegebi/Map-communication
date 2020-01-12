package com.bawei6.usermodule;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.basemvp.BaseContract;
import com.bawei6.basemodule.basemvp.Presenter;
import com.bawei6.basemodule.bean.CreateGroupBodyBean;
import com.bawei6.basemodule.bean.FindGroupBean;
import com.bawei6.basemodule.bean.FindGroupNameBean;
import com.bawei6.basemodule.bean.GetMyGroupBean;
import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.ScoureBean;
import com.bawei6.basemodule.bean.UserFriBean;
import com.bawei6.basemodule.titlebar.NormalTitBar;
import com.baweigame.xmpplibrary.XmppManager;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatException;
import org.jxmpp.jid.parts.Resourcepart;
import org.jxmpp.stringprep.XmppStringprepException;

import java.util.List;

public class NewGroupActivity extends AppCompatActivity implements BaseContract.BaseView {

    private EditText edi_addFri,edi_jian,edi_pwd;
    private TextView tv_addFri;
    private RecyclerView recyclerView;
    private NormalTitBar normalTitBar;
    private Presenter presenter;
    private CreateGroupBodyBean createGroupBodyBean=new CreateGroupBodyBean();

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 110:
                    Boolean flag= (Boolean) msg.obj;
                    String pwd = edi_pwd.getText().toString();
                    MultiUserChat multiUserChat = XmppManager.getInstance().getXmppChatRoomManager()
                            .createRoom(createGroupBodyBean.getGroupname(), pwd);
                    try {
                        multiUserChat.create(Resourcepart.from(createGroupBodyBean.getGroupname()));
                    } catch (SmackException.NoResponseException e) {
                        e.printStackTrace();
                    } catch (XMPPException.XMPPErrorException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (MultiUserChatException.MucAlreadyJoinedException e) {
                        e.printStackTrace();
                    } catch (SmackException.NotConnectedException e) {
                        e.printStackTrace();
                    } catch (MultiUserChatException.MissingMucCreationAcknowledgeException e) {
                        e.printStackTrace();
                    } catch (MultiUserChatException.NotAMucServiceException e) {
                        e.printStackTrace();
                    } catch (XmppStringprepException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(NewGroupActivity.this, ""+flag, Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        edi_addFri=findViewById(R.id.new_group_edi_name);
        edi_jian=findViewById(R.id.new_grouuo_edi_jian);
        tv_addFri=findViewById(R.id.new_group_tv_addFri);
        edi_pwd=findViewById(R.id.new_group_edi_pwd);
        normalTitBar=findViewById(R.id.new_group_normal_tit);

        presenter=new Presenter();
        presenter.setAttachVie(this);

        tv_addFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        normalTitBar.titleOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = edi_addFri.getText().toString();
                String s1 = edi_jian.getText().toString();

                createGroupBodyBean.setId(1);
                createGroupBodyBean.setGroupname(s);
                createGroupBodyBean.setGroupnumber(3);
                createGroupBodyBean.setGroupdesc(s1);
                createGroupBodyBean.setCreateor("sample string 5");
                createGroupBodyBean.setGrouptypeid(6);
                createGroupBodyBean.setGrouptypename("sample string 7");
                createGroupBodyBean.setGrouptypeimg("sample string 8");
                createGroupBodyBean.setGroupimg("sample string 9");
                presenter.loadGroupBeanP(createGroupBodyBean);

            }
        });


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
        Message obtain = Message.obtain();
        obtain.what=110;
        obtain.obj=flag;
        handler.sendMessage(obtain);
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
    public void showOutGroup(boolean flag) {

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
