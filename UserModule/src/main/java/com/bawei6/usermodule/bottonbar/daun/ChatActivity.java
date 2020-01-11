package com.bawei6.usermodule.bottonbar.daun;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.AliyunUtils;
import com.bawei6.common.LogUtils;
import com.bawei6.immodule.entity.BaseMsg;
import com.bawei6.immodule.entity.MsgType;
import com.bawei6.immodule.msg.MsgManager;
import com.bawei6.immodule.notify.IObserver;
import com.bawei6.immodule.notify.NotifyManager;
import com.bawei6.usermodule.CallBackOnclick;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.ChatGridAdapter;
import com.bawei6.usermodule.adapter.RecyChatAdapter;
import com.bawei6.usermodule.bean.ChatBean;
import com.bawei6.usermodule.bean.StartAudioActivity;
import com.baweigame.xmpplibrary.XmppManager;
import com.hw.videoprocessor.VideoProcessor;
import com.ilike.voicerecorder.widget.VoiceRecorderView;
import com.wildma.pictureselector.PictureSelector;

import org.jivesoftware.smack.AbstractXMPPConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private static final int VIDEO_CODE = 999;
    private RecyclerView recyclerView;
    private RecyChatAdapter recyChatAdapter;
    private List<ChatBean> list=new ArrayList<>();
    private EditText chat_edi_msh;
    private Button chat_btn_send;
    private ImageView chat_img_pic,chat_img_yu,chat_img_zao,chat_img_face,chat_img_gps;
    private VoiceRecorderView voiceRecorderView;

    private GridView gridView;
    private List<String> gridList=new ArrayList<>();
    private ChatGridAdapter chatGridAdapter;

    private String jid="@api.city2sky";
    String mycode;
    String myname;
    String fricode;
    String friname;

    private MediaPlayer mediaPlayer=new MediaPlayer();

    private AudioRecoderUtils audioRecoderUtils;

    private Handler handler=new Handler();

    //图片上传地址
    private String imgUpdateName;
    //语言上传地址
    private String yuUpdataName;
    //视屏上传地址
    private String videoUpdataName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView=findViewById(R.id.chat_recy);
        chat_btn_send=findViewById(R.id.chat_btn_send);
        chat_edi_msh=findViewById(R.id.chat_edi_msg);
        chat_img_pic=findViewById(R.id.chat_img_pic);
        chat_img_yu=findViewById(R.id.chat_img_yu);
        chat_img_zao=findViewById(R.id.chat_img_zao);
        chat_img_face=findViewById(R.id.chat_img_face);
        gridView=findViewById(R.id.chat_grid);
        chat_img_gps=findViewById(R.id.chat_img_gps);



        voiceRecorderView=findViewById(R.id.voice_recorder);

        final PopupWindow popupWindow = new PopupWindow(this);

        gridList.add("\uD83D\uDE00");
        gridList.add("\uD83D\uDE03");
        gridList.add("\uD83D\uDE05");
        gridList.add("\uD83D\uDE07");
        gridList.add("\uD83D\uDE0D");
        gridList.add("\uD83D\uDE18");
        gridList.add("\uD83D\uDE12");
        gridList.add("\uD83D\uDE34");
        gridList.add("\uD83D\uDE2D");
        gridList.add("\uD83D\uDE21");
        gridList.add("\uD83D\uDE08");
        gridList.add("\uD83D\uDE4A");
        gridList.add("\uD83D\uDE36");
        gridList.add("\uD83D\uDE33");
        gridList.add("\uD83D\uDE30");
        gridList.add("\uD83D\uDE16");
        gridList.add("\uD83D\uDC79");
        gridList.add("\uD83D\uDC9D");

        chatGridAdapter=new ChatGridAdapter(ChatActivity.this,gridList);
        gridView.setAdapter(chatGridAdapter);

        View inflate = LayoutInflater.from(this).inflate(R.layout.microphone, null);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(200);
        popupWindow.setWidth(200);

        final Intent intent = getIntent();
        mycode = intent.getStringExtra("userlianfralogcode");
        myname = intent.getStringExtra("userlianfralogname");
        fricode = intent.getStringExtra("userlianfrafricode");
        friname = intent.getStringExtra("userlianfrafriname");

        LogUtils.i("chat---->"+mycode);
        LogUtils.i("chat---->"+myname);
        LogUtils.i("chat---->"+fricode);
        LogUtils.i("chat---->"+friname);

        imgUpdateName=mycode+System.currentTimeMillis()+"ghy.jpg";
        yuUpdataName =mycode+System.currentTimeMillis()+".mp3";
        videoUpdataName=mycode+System.currentTimeMillis()+".mp4";


        audioRecoderUtils=new AudioRecoderUtils(mycode);


        new Thread(new Runnable() {
            @Override
            public void run() {
                AbstractXMPPConnection connection = XmppManager.getInstance().getConnection();
                Boolean aBoolean = XmppManager.getInstance().checkConnect();
            }
        }).start();



        recyChatAdapter=new RecyChatAdapter(this,list);
        recyclerView.setAdapter(recyChatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //监听消息的接收
                NotifyManager.getInstance().addObserver(new IObserver() {
                    @Override
                    public void nodify(final BaseMsg msg) {

                        LogUtils.i(msg.getMsg());
                        LogUtils.i(msg.getMsgType()+"");

                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                if(msg.getMsg().contains(".jpg")){
                                    Log.i("ResultAK47",msg.getMsg());
                                    AliyunUtils.getInstance().download("baweitest6",msg.getMsg(),"/sdcard/Pictures/"+msg.getMsg());
                                    list.add(new ChatBean(SendType.imgType,0,"/sdcard/Pictures/"+msg.getMsg()));
                                    recyChatAdapter.notifyDataSetChanged();
                                 }else if(msg.getMsg().contains(".mp3")){
                                    Log.i("ResultAK47",msg.getMsg());
                                    AliyunUtils.getInstance().download("baweitest6",msg.getMsg(),"/sdcaird/Pictures/"+msg.getMsg());
                                    list.add(new ChatBean(SendType.audioType,0,"/sdcard/Pictures/"+msg.getMsg()));
                                    recyChatAdapter.notifyDataSetChanged();
                                  }else {
                                    list.add(new ChatBean(SendType.textType,0,msg.getMsg()));
                                    recyChatAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                });
            }
        });

        //adapter点击事件

        recyChatAdapter.getClick(new CallBackOnclick() {
            @Override
            public void getOnclick(View view, int position) {

                if (view.getId() == R.id.chat_recy_img_left_yu) {

                    String msg = list.get(position).getMsg();
                    try {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(msg);
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                if(mediaPlayer.isPlaying()){
                                    mediaPlayer.pause();
                                }else {
                                    mediaPlayer.start();
                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (view.getId()==R.id.chat_recy_img_right_yu){
                    String msg = list.get(position).getMsg();
                    try {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(msg);
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {

                                if(mediaPlayer.isPlaying()){
                                    mediaPlayer.pause();
                                }else {
                                    mediaPlayer.start();
                                }

                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(view.getId()==R.id.chat_recy_img_left_pic){
                    Toast.makeText(ChatActivity.this, "left视屏", Toast.LENGTH_SHORT).show();
                    String msg = list.get(position).getMsg();
                    Intent intent1 = new Intent(ChatActivity.this, StartAudioActivity.class);
                    intent1.putExtra("paths",msg);
                    startActivity(intent1);
                }

                if(view.getId()==R.id.chat_recy_img_right_pic){
                    Toast.makeText(ChatActivity.this, "right视屏", Toast.LENGTH_SHORT).show();
                    String msg = list.get(position).getMsg();
                    Intent intent1 = new Intent(ChatActivity.this, StartAudioActivity.class);
                    intent1.putExtra("paths",msg);
                    startActivity(intent1);
                }
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chat_edi_msh.append(gridList.get(i));
            }
        });

        chat_img_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(ChatActivity.this,ChatMapActivity.class);
                startActivityForResult(mapIntent,1111);

            }
        });

        chat_img_zao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);//设置频道
                startActivityForResult(intent,VIDEO_CODE);
            }
        });

        chat_img_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    gridView.setVisibility(View.VISIBLE);
            }
        });

        chat_btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String msg = chat_edi_msh.getText().toString();

                if(msg.isEmpty()){http://59.110.191.1/img/4cd6bba4e59d47bdb94a59d69e04b69c1578708270685ghy.jpg
                    Toast.makeText(ChatActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    //发送消息
                    BaseMsg baseMsg = new BaseMsg(myname, friname + jid, msg, MsgType.TXT);
                    MsgManager.getInstance().sendMsg(baseMsg);

                    //适配器更新发送的文本信息
                    list.add(new ChatBean(SendType.textType,1,msg));
                    recyChatAdapter.notifyDataSetChanged();
                }

                    }
//                }).start();

//            }
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
            public boolean onTouch(View view, final MotionEvent motionEvent) {
                return voiceRecorderView.onPressToSpeakBtnTouch(view, motionEvent, new VoiceRecorderView.EaseVoiceRecorderCallback() {
                    @Override
                    public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                        AliyunUtils.getInstance().upload("baweitest6","audio/"+yuUpdataName,voiceFilePath);
                        BaseMsg baseMsg = new BaseMsg(myname, friname + jid, "audio/" + yuUpdataName, MsgType.AUDIO);
                        MsgManager.getInstance().sendMsg(baseMsg);
                        list.add(new ChatBean(SendType.audioType,1,"http://baweitest6.oss-cn-beijing.aliyuncs.com/"+"audio/"+yuUpdataName));
                    recyChatAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                //图片地址
                final String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);

                AliyunUtils.getInstance().upload("baweitest6","img/"+imgUpdateName,picturePath);
                BaseMsg baseMsg = new BaseMsg(myname, friname + jid, "img/"+imgUpdateName, MsgType.IMG);

                Log.i("ResultAK47","img/"+imgUpdateName);

                MsgManager.getInstance().sendMsg(baseMsg);
                list.add(new ChatBean(SendType.imgType,1,picturePath));
                recyChatAdapter.notifyDataSetChanged();
            }
        }

        if(requestCode == VIDEO_CODE && resultCode == Activity.RESULT_OK){
            Uri u = data.getData();
            String s = u.toString();
                    try {
                        VideoProcessor.processor(this)
                                .input(s)
                                .output(s)
                                .outWidth(80)
                                .outHeight(80)
                                .process();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    AliyunUtils.getInstance().upload("baweitest6","video/"+videoUpdataName,s);
                    BaseMsg baseMsg = new BaseMsg(myname, friname + jid, "video/" + videoUpdataName, MsgType.VIDEO);
                    Log.i("ResultAK47","audio/" + videoUpdataName);
                    MsgManager.getInstance().sendMsg(baseMsg);
                     list.add(new ChatBean(SendType.imgType,1,s));
                     recyChatAdapter.notifyDataSetChanged();
        }

    }

}
