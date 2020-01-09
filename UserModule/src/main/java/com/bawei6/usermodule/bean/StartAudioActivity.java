package com.bawei6.usermodule.bean;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei6.usermodule.R;
import com.shuyu.gsyvideoplayer.video.GSYSampleADVideoPlayer;

import java.util.ArrayList;

public class StartAudioActivity extends AppCompatActivity  {

    private GSYSampleADVideoPlayer.GSYADVideoModel ak1,ak2;
    private GSYSampleADVideoPlayer standardGSYVideoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_audio);


        String paths = getIntent().getStringExtra("paths");

        Log.i("AKAKAK",paths);

        standardGSYVideoPlayer=findViewById(R.id.gsy);
        ak1=new GSYSampleADVideoPlayer.GSYADVideoModel(paths,"正式视频", GSYSampleADVideoPlayer.GSYADVideoModel.TYPE_NORMAL);
        ArrayList<GSYSampleADVideoPlayer.GSYADVideoModel> list=new ArrayList<>();
        list.add(ak1);
        standardGSYVideoPlayer.setAdUp(list,true,0);
        standardGSYVideoPlayer.startPlayLogic();

    }
}
