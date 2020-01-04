package com.bawei6.usermodule.log;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.bawei6.basemodule.basemvp.BaseContract;
import com.bawei6.basemodule.basemvp.Presenter;
import com.bawei6.basemodule.bean.LogBean;
import com.bawei6.basemodule.bean.LogBodyBean;
import com.bawei6.basemodule.bean.ResBodyBean;
import com.bawei6.basemodule.bean.ScoureBean;
import com.bawei6.basemodule.bean.UserFriBean;
import com.bawei6.usermodule.bottonbar.lianxiren.LianxirenActivity;
import com.bawei6.usermodule.R;
import com.baweigame.xmpplibrary.XmppManager;

import java.util.List;

public class LogActivity extends AppCompatActivity  implements BaseContract.BaseView {

    private EditText edi_name,edi_pwd;
    private Button btn_log,btn_res;
    private Presenter presenter=new Presenter();
    private ResBodyBean resBodyBean=new ResBodyBean();
    private LogBodyBean logBodyBean=new LogBodyBean();
    private CheckBox user_log_ck;
    private SharedPreferences sharedPreferences;
    private TextView user_tv_update;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 101:
                    Bundle bundle= (Bundle) msg.obj;
                    String logResult = bundle.getString("web");
                    boolean im = bundle.getBoolean("im");
                    String usercode = bundle.getString("usercode");
                    int code = bundle.getInt("logcode");

                    Log.i("AK47","ImServerHandler"+im);
                    Log.i("AK47","WebServerHandler"+logResult);
                    Log.i("AK47","userCodeHandler"+usercode);
                    Log.i("AK47","CodeHandler"+usercode);

                    if(im==true&&code==200){
                        Toast.makeText(LogActivity.this,
                                "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LogActivity.this, LianxirenActivity.class);
                        intent.putExtra("codde",usercode);
                        startActivity(intent);
//                        ARouter.getInstance().build(ArouterUtils.MapView).withString("code",usercode).navigation();
                    }else {
                        Toast.makeText(LogActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        edi_name=findViewById(R.id.edi_name);
        edi_pwd=findViewById(R.id.edi_pwd);
        btn_log=findViewById(R.id.btn_log);
        btn_res=findViewById(R.id.btn_res);
        user_log_ck=findViewById(R.id.user_log_ck);
        user_tv_update=findViewById(R.id.user_tv_update);


        presenter.setAttachVie(this);

        sharedPreferences=getSharedPreferences("Map",MODE_PRIVATE);
        final SharedPreferences.Editor edit = sharedPreferences.edit();


        user_log_ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    String name = edi_name.getText().toString().trim();
                    String pwd = edi_pwd.getText().toString().trim();
                    edit.putString("edi_name",name);
                    edit.putString("edi_pwd",pwd);
                }else {
                    edit.clear();
                }
            }
        });


        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String name = edi_name.getText().toString();
                 String pwd = edi_pwd.getText().toString();
                 logBodyBean.setUsername(name);
                 logBodyBean.setPwd(pwd);
                 presenter.LoadLogBeanP(name,pwd,logBodyBean);

            }
        });

        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               final String name = edi_name.getText().toString();
               final String pwd = edi_pwd.getText().toString();

                resBodyBean.setId(1);
                resBodyBean.setUsercode("sample string 2");
                resBodyBean.setUsername(name);
                resBodyBean.setPwd(pwd);
                resBodyBean.setSex("1");
                resBodyBean.setBirthday("sample string 6");
                resBodyBean.setHeaderimg("sample string 7");
                resBodyBean.setNick("sample string 8");
                resBodyBean.setUtype(9);
                resBodyBean.setImuseraccount("sample string 10");
                resBodyBean.setSigndesc("sample string 11");
                resBodyBean.setOpenlocation(12);
                resBodyBean.setOpenmsgalert(13);

                presenter.LoadResBeanP(name,pwd,resBodyBean);
            }
        });

        user_tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogActivity.this,UpdateActivity.class));
            }
        });
    }

    @Override
    public void showResult(final String msg) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                final   String name = edi_name.getText().toString();
                final String pwd = edi_pwd.getText().toString();
                Looper.prepare();
                XmppManager.getInstance().getXmppUserManager().createAccount(name,pwd);
                Looper.loop();
            }
        }).start();
        Toast.makeText(LogActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLogResult(List<LogBean.DataBean> list, final String logResult, final int code) {
        final   String name = edi_name.getText().toString();
        final String pwd = edi_pwd.getText().toString();
        final String usercode = list.get(0).getUsercode();
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean login = XmppManager.getInstance().getXmppUserManager().login(name, pwd);

                Log.i("AK47","ImServer"+login);
                Log.i("AK47","WebServer"+logResult);
                Log.i("AK47","code"+usercode);
                Log.i("AK47","返回"+code);

                Message obtain = Message.obtain();
                obtain.what=101;
                Bundle bundle = new Bundle();
                bundle.putString("web",logResult);
                bundle.putBoolean("im",login);
                bundle.putString("usercode",usercode);
                bundle.putInt("logcode",code);
                obtain.obj=bundle;
                handler.sendMessage(obtain);
            }
        }).start();
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
