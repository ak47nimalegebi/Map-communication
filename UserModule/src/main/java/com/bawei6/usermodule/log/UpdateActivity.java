package com.bawei6.usermodule.log;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.bawei6.basemodule.basemvp.BaseContract;
import com.bawei6.basemodule.basemvp.Presenter;
import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.ScoureBean;
import com.bawei6.basemodule.bean.UpdateBodyBean;
import com.bawei6.basemodule.bean.UserFriBean;
import com.bawei6.usermodule.R;
import com.baweigame.xmpplibrary.XmppManager;

import java.util.List;

public class UpdateActivity extends AppCompatActivity implements BaseContract.BaseView {

    private EditText edi_update_id,edi_update_pwd;
    private Button btn_update_ok;
    private Presenter presenter=new Presenter();
    private UpdateBodyBean updateBodyBean=new UpdateBodyBean();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 001:
                    Bundle bundle= (Bundle) msg.obj;
                    String updateResult = bundle.getString("updateResult");
                    boolean changePassword = bundle.getBoolean("changePassword");
                    if(updateResult.equals("操作成功")&&changePassword==true){
                        Toast.makeText(UpdateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(UpdateActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edi_update_id=findViewById(R.id.edi_update_id);
        edi_update_pwd=findViewById(R.id.edi_update_pwd);
        btn_update_ok=findViewById(R.id.btn_update_ok);

        presenter.setAttachVie(this);

        btn_update_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edi_update_id.getText().toString();
                String pwd = edi_update_pwd.getText().toString();
                updateBodyBean.setUserid(id);
                updateBodyBean.setPwd(pwd);
                presenter.LoadUpdateBeanP(id, pwd, updateBodyBean);
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
    public void showUpdateResult(final String updateResult) {
        Toast.makeText(this, updateResult, Toast.LENGTH_SHORT).show();
        final String pwd = edi_update_pwd.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean b = XmppManager.getInstance().getXmppUserManager().changePassword(pwd);

                Message message = Message.obtain();
                message.what=001;
                Bundle bundle = new Bundle();
                bundle.putString("updateResult",updateResult);
                bundle.putBoolean("changePassword",b);
            }
        }).start();
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
