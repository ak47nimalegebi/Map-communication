package com.bawei6.usermodule;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei6.common.LogUtils;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.entity.MsgEntity;

import org.jivesoftware.smack.chat2.Chat;

public class ImActivity extends AppCompatActivity {

    private Button btn_send,btn_add;
    private EditText edi_friend,edi_mess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);

        btn_add=findViewById(R.id.btn_add);
        btn_send=findViewById(R.id.btn_send);
        edi_friend=findViewById(R.id.edi_friend);
        edi_mess=findViewById(R.id.edi_friend);



        XmppManager.getInstance().addMessageListener(new IMsgCallback() {
            @Override
            public void Success(MsgEntity msgEntity) {
                String msg = msgEntity.getMsg();
                LogUtils.i(msg);
            }

            @Override
            public void Failed(Throwable throwable) {
                LogUtils.i(throwable.getMessage());
            }
        });


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String friend = edi_friend.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        boolean b = XmppManager.getInstance().getXmppFriendManager().addFriend(friend, friend);
                        LogUtils.i("添加"+b);
                        Looper.loop();
                    }
                }).start();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String s = edi_friend.getText().toString();
                final String mess = edi_mess.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Chat chat = XmppManager.getInstance().getXmppMsgManager().getFriendChat(s+"@api.city2sky/7aici18ov3");
                        XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(chat,mess);

                    }
                }).start();
            }
        });
    }
}
