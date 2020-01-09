package com.bawei6.usermodule.bottonbar.daun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.AliyunUtils;
import com.bawei6.usermodule.CallBackOnclick;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.ChatGridAdapter;
import com.bawei6.usermodule.adapter.RecyChatAdapter;
import com.bawei6.usermodule.bean.ChatBean;
import com.bawei6.usermodule.bean.StartAudioActivity;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.entity.MsgEntity;
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

    private String outPath="/sdcard/Pictures/"+mycode+System.currentTimeMillis()+".mp4";


    private MediaPlayer mediaPlayer=new MediaPlayer();

    private ImageView mic_img;
    private TextView mic_tv;

    private String imgType="/img/";
    private String audioType="/audio/";
    private String videoType="/video/";




    private AudioRecoderUtils audioRecoderUtils;


    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 101:
                    Bundle bundle= (Bundle) msg.obj;
                    String send_msg = bundle.getString("send_msg");
                    list.add(new ChatBean(SendType.textType,1,send_msg,null));
                    recyChatAdapter.notifyDataSetChanged();
                    break;
                case 102:

                    Bundle bundle2= (Bundle) msg.obj;
                    String get_msg = bundle2.getString("get_msg");
                    list.add(new ChatBean(SendType.textType,0,get_msg,null));
                    recyChatAdapter.notifyDataSetChanged();

                    break;

                case 103:
                    Bundle bundle3= (Bundle) msg.obj;
                    String send_img = bundle3.getString("send_img");
                    list.add(new ChatBean(SendType.imgType,1,send_img,null));
                    recyChatAdapter.notifyDataSetChanged();
                    break;

                case 104:
                    Bundle bundle4= (Bundle) msg.obj;
                    String get_pic = bundle4.getString("get_pic");
                    AliyunUtils.getInstance().download("baweitest6",get_pic,"/sdcard/Pictures/"+get_pic+".jpg");
                    list.add(new ChatBean(SendType.imgType,0,"/sdcard/Pictures/"+get_pic+".jpg",null));
                    recyChatAdapter.notifyDataSetChanged();
                    break;

                case 105:
                    String path= (String) msg.obj;
                    Log.i("audioType","handler接受:--->"+path);
                    list.add(new ChatBean(SendType.audioType,1,path,null));
                    recyChatAdapter.notifyDataSetChanged();
                    break;

                case 106:
                    String path2= (String) msg.obj;

                    AliyunUtils.getInstance().download("baweitest6",path2,"/sdcard/Pictures/"+path2);
                    Log.i("audioType","下载的路径--->"+path2);
                    Log.i("audioType","下载的保存的路径--->"+"/sdcard/Pictures/"+path2);

                    list.add(new ChatBean(SendType.audioType,0,"/sdcard/Pictures/"+path2,null));
                    recyChatAdapter.notifyDataSetChanged();
                    break;

                case 107:
                    String img_url= (String) msg.obj;
                    Log.i("videoPaht","107"+img_url);
                    list.add(new ChatBean(SendType.imgType,1,img_url,null));
                    recyChatAdapter.notifyDataSetChanged();
                    break;

                case 108:
                    String videoPaht= (String) msg.obj;
                    Log.i("videoPathsss",videoPaht);
                    list.add(new ChatBean(SendType.imgType,0,videoPaht,null));
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
        mic_img=inflate.findViewById(R.id.mic_img);
        mic_tv=inflate.findViewById(R.id.mic_tv);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(200);
        popupWindow.setWidth(200);

        final Intent intent = getIntent();
        mycode = intent.getStringExtra("mycode");
        myname = intent.getStringExtra("myname");
        fricode = intent.getStringExtra("fricode");
        friname = intent.getStringExtra("friname");

        audioRecoderUtils=new AudioRecoderUtils(mycode);
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

//        fileName= Environment.getExternalStorageDirectory().getAbsolutePath()+"/soundrecord/amrsend.amr";

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

                        if(msg.contains("video")){
                            Message obtain = Message.obtain();
                            obtain.what=108;
                            obtain.obj=msgEntity.getMsg();
                            handler.sendMessage(obtain);
                            Log.i("videoPathsss",msgEntity.getMsg());
                        }else if(msg.contains(".mp3")){
                            Message obtain = Message.obtain();
                            obtain.what=106;
                            obtain.obj=msgEntity.getMsg();
                            handler.sendMessage(obtain);
                            Log.i("audioType","接受的信息--->"+msgEntity.getMsg());
                        }else if(msg.contains(".jpg")){
                            Message obtain = Message.obtain();
                            obtain.what=104;
                            Bundle bundle = new Bundle();
                            bundle.putString("get_pic",msg);
                            obtain.obj=bundle;
                            handler.sendMessage(obtain);
                            Log.i("pic_img",msg);
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


        //adapter点击事件

        recyChatAdapter.getClick(new CallBackOnclick() {
            @Override
            public void getOnclick(View view, int position) {

                if (view.getId() == R.id.chat_recy_img_left_yu) {

                    String msg = list.get(position).getMsg();
                    Log.i("msgggggg",msg);
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
                    Log.i("msgggggg",msg);
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
            public boolean onTouch(View view, final MotionEvent motionEvent) {
                return voiceRecorderView.onPressToSpeakBtnTouch(view, motionEvent, new VoiceRecorderView.EaseVoiceRecorderCallback() {
                    @Override
                    public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                            String fileName =mycode+System.currentTimeMillis()+".mp3";
                            Log.i("yu",voiceFilePath);
                        AliyunUtils.getInstance().upload("baweitest6",fileName,voiceFilePath);
                        XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(friname+jid,fileName);

                        Message obtain = Message.obtain();
                        obtain.what=105;
                        obtain.obj="http://baweitest6.oss-cn-beijing.aliyuncs.com/"+fileName;
                        handler.sendMessage(obtain);

                        Log.i("audioType","上传路径:--->"+voiceFilePath);
                        Log.i("audioType","上传保存的路径:--->"+fileName);
                        Log.i("audioType","加载路径:--->"+"http://baweitest6.oss-cn-beijing.aliyuncs.com"+fileName);
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
                //上传名称
                final String updateName=System.currentTimeMillis()+"ghy.jpg";
                Log.i("img_picturePath",picturePath);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message obtain = Message.obtain();
                        obtain.what=103;
                        Bundle bundle = new Bundle();
                        bundle.putString("send_img",picturePath);
                        obtain.obj=bundle;
                        handler.sendMessage(obtain);
                        AliyunUtils.getInstance().upload("baweitest6",updateName,picturePath);
                        XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(friname+jid,updateName);

                    }
                }).start();

            }
        }

        if(requestCode == VIDEO_CODE && resultCode == Activity.RESULT_OK){
            final Uri uri = data.getData();//视频uri路径
            final String s = uri.toString();
            Log.i("videoPaht","in"+uri);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        VideoProcessor.processor(ChatActivity.this)
                                .input(s)
                                .output(s)
                                //以下参数全部为可选
                                .outWidth(80)
                                .outHeight(80)
                                .process();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    AliyunUtils.getInstance().upload("baweitest6",mycode+System.currentTimeMillis()+".jpg",s);
                    XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(friname+jid,s);
                    Message obtain = Message.obtain();
                    obtain.what=107;
                    obtain.obj=s;
                    handler.sendMessage(obtain);
                }
            }).start();

        }

        if(requestCode==1111){

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
