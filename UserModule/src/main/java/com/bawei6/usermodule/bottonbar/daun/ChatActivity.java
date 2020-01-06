package com.bawei6.usermodule.bottonbar.daun;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.AliyunUtils;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.RecyChatAdapter;
import com.bawei6.usermodule.bean.ChatBean;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.entity.MsgEntity;
import com.ilike.voicerecorder.widget.VoiceRecorderView;
import com.wildma.pictureselector.PictureSelector;

import org.jivesoftware.smack.AbstractXMPPConnection;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyChatAdapter recyChatAdapter;
    private List<ChatBean> list=new ArrayList<>();
    private EditText chat_edi_msh;
    private Button chat_btn_send;
    private ImageView chat_img_pic,chat_img_yu;
    private VoiceRecorderView voiceRecorderView;

    private String jid="@api.city2sky";
    String mycode;
    String myname;
    String fricode;
    String friname;

    private MediaPlayer mediaPlayer=new MediaPlayer();
    private MediaRecorder mediaRecorder=new MediaRecorder();
    private String fileName;

    private ImageView mic_img;
    private TextView mic_tv;

    private AudioRecoderUtils audioRecoderUtils;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 101:
                    Bundle bundle= (Bundle) msg.obj;
                    String send_msg = bundle.getString("send_msg");
                    list.add(new ChatBean(SendType.textType,1,send_msg));
                    recyChatAdapter.notifyDataSetChanged();
                    break;
                case 102:

                    Bundle bundle2= (Bundle) msg.obj;
                    String get_msg = bundle2.getString("get_msg");
                    list.add(new ChatBean(SendType.textType,0,get_msg));
                    recyChatAdapter.notifyDataSetChanged();

                    break;

                case 103:
                    Bundle bundle3= (Bundle) msg.obj;
                    String send_img = bundle3.getString("send_img");
                    list.add(new ChatBean(SendType.imgType,1,send_img));
                    recyChatAdapter.notifyDataSetChanged();
                    break;

                case 104:

                    Bundle bundle4= (Bundle) msg.obj;
                    String get_pic = bundle4.getString("get_pic");
                    list.add(new ChatBean(SendType.imgType,0,get_pic));
                    recyChatAdapter.notifyDataSetChanged();

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView=findViewById(R.id.chat_recy);
        chat_btn_send=findViewById(R.id.chat_btn_send);
        chat_edi_msh=findViewById(R.id.chat_edi_msg);
        chat_img_pic=findViewById(R.id.chat_img_pic);
        chat_img_yu=findViewById(R.id.chat_img_yu);
        voiceRecorderView=findViewById(R.id.voice_recorder);




        final PopupWindow popupWindow = new PopupWindow(this);

        View inflate = LayoutInflater.from(this).inflate(R.layout.microphone, null);
        mic_img=inflate.findViewById(R.id.mic_img);
        mic_tv=inflate.findViewById(R.id.mic_tv);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(200);
        popupWindow.setWidth(200);


        audioRecoderUtils=new AudioRecoderUtils();
        audioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
            @Override
            public void onUpdate(double db, long time) {
//                mic_img.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
//                mic_tv.setText(time+"");
            }

            @Override
            public void onStop(String filePath) {
//                Toast.makeText(ChatActivity.this, "录音保存在：" + filePath, Toast.LENGTH_SHORT).show();
//                mic_tv.setText(0);
            }
        });


        Intent intent = getIntent();
        mycode = intent.getStringExtra("mycode");
        myname = intent.getStringExtra("myname");
         fricode = intent.getStringExtra("fricode");
         friname = intent.getStringExtra("friname");

        fileName= Environment.getExternalStorageDirectory().getAbsolutePath()+"/soundrecord/amrsend.amr";

        new Thread(new Runnable() {
            @Override
            public void run() {
                AbstractXMPPConnection connection = XmppManager.getInstance().getConnection();
                Boolean aBoolean = XmppManager.getInstance().checkConnect();
                Log.i("chatAAAA",aBoolean+"");
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                XmppManager.getInstance().addMessageListener(new IMsgCallback() {
                    @Override
                    public void Success(MsgEntity msgEntity) {
                        MsgEntity.MsgType msgType = msgEntity.getMsgType();

                        String msg = msgEntity.getMsg();

                        if(msg.contains(".jpg")){
                            Message obtain = Message.obtain();
                            obtain.what=104;
                            Bundle bundle = new Bundle();
                            bundle.putString("get_pic",msg);
                            obtain.obj=bundle;
                            handler.sendMessage(obtain);
                        }else {
                            Message obtain = Message.obtain();
                            obtain.what=102;
                            Bundle bundle = new Bundle();
                            bundle.putString("get_msg",msg);
                            obtain.obj=bundle;
                            handler.sendMessage(obtain);
                        }

                        Log.i("chatAAAA",msg);
                        Log.i("chatAAAA",msgType+"");
                    }

                    @Override
                    public void Failed(Throwable throwable) {
                        Log.i("chatAAAA",throwable.getMessage());
                    }
                });

            }
        }).start();


        recyChatAdapter=new RecyChatAdapter(this,list);
        recyclerView.setAdapter(recyChatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        chat_btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String msg = chat_edi_msh.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message obtain = Message.obtain();
                        obtain.what=101;
                        Bundle bundle = new Bundle();
                        bundle.putString("send_msg",msg);
                        obtain.obj=bundle;
                        handler.sendMessage(obtain);
                        XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(friname+jid,msg);
                    }
                }).start();

            }
        });

        chat_img_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureSelector
                        .create(ChatActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
            }
        });

      chat_img_yu.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View view, MotionEvent motionEvent) {
//              switch (motionEvent.getAction()){
//                  case MotionEvent.ACTION_DOWN:
//                      popupWindow.showAtLocation(recyclerView,Gravity.CENTER,0,0);
//                      voiceRecorderView.setVisibility(View.VISIBLE);
//                      audioRecoderUtils.startRecord();
//                      break;
//                  case MotionEvent.ACTION_UP:
//                      audioRecoderUtils.stopRecord();        //结束录音（保存录音文件）
//                      voiceRecorderView.setVisibility(View.GONE);
//                      popupWindow.dismiss();
////                    XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(friname+jid,picturePath);
//                      break;
//              }
              fileName=mycode+".mp3";
              AliyunUtils.getInstance().upload("baweitest6/img",fileName,);
              return true;
          }
      });

//        chat_img_yu.setOnTouchListener((v, event) -> ease_voice.onPressToSpeakBtnTouch(v, event, (voiceFilePath, voiceTimeLength) -> {
//            String fileName = System.currentTimeMillis()+".mp3";
//            String remotePath = "http://baweitest6.oss-cn-beijing.aliyuncs.com/"+fileName;
//            AliyunUtils.getInstance().upload(fileName, voiceFilePath, new OSSCompletedCallback() {
//                @Override
//                public void onSuccess(OSSRequest request, OSSResult result) {
//                    msgManager.sendSingleMessage(chat,remotePath);
//                }
//
//                @Override
//                public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
//                    dataList.get(dataList.size()-1).setError(View.VISIBLE);
//                    dataList.get(dataList.size()-1).setSuccess(View.INVISIBLE);
//                    chatAdapter.notifyDataSetChanged();
//                }
//            });
//            MsgEntity msgEntity = new MsgEntity(MapChatApp.currentUser.getUsername() + "@" + XmppManager.getInstance().getXmppConfig().getDomainName(),
//                    chatUser.getUsername() + "@" + XmppManager.getInstance().getXmppConfig().getDomainName(),
//                    remotePath,
//                    MsgEntity.AUDIO,
//                    View.VISIBLE,
//                    View.INVISIBLE,
//                    MapChatApp.currentUser.getHeaderimg(),
//                    System.currentTimeMillis(),
//                    null);
//            dataList.add(msgEntity);
//            chatAdapter.notifyDataSetChanged();
//            mRecycler.scrollToPosition(dataList.size()-1);
//            DaoUtils.getInstance().insertMsg(msgEntity);
//        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                final String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                Log.i("chatAAAA",picturePath);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message obtain = Message.obtain();
                        obtain.what=103;
                        Bundle bundle = new Bundle();
                        bundle.putString("send_img",picturePath);
                        obtain.obj=bundle;
                        handler.sendMessage(obtain);
                        AliyunUtils.getInstance().upload("baweitest6/img","up.jpg",picturePath);
                        XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(friname+jid,picturePath);
                    }
                }).start();

//                mIvImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                /*如果使用 Glide 加载图片，则需要禁止 Glide 从缓存中加载，因为裁剪后保存的图片地址是相同的*/
//                RequestOptions requestOptions = RequestOptions
//                        .circleCropTransform()
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(true);
//                Glide.with(this).load(picturePath).apply(requestOptions).into(mIvImage);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
