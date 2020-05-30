package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class Music_Ativity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    private MediaPlayer player;//声明播放器
    private int musicIndex;    //歌曲位置:索引
    private File dir;          //获取SD卡的路径
    private String []musicData; //歌曲列表
    private TextView tvMusicTitlle; //歌曲的标题
    private ImageButton ibPlayOrPause; //播放和暂停按钮
    private int currenctTime;   //暂停时的当前时间
    //    private ListView lv;       //列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music__ativity);
        getMusicData();  // 用于获取音乐数据源
        initViewContent();//用于初始化对象
    }
    protected void getMusicData(){
        //获取sd卡中的歌曲信息
        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        //把歌曲信息存放到数组中(数据源)
        musicData = dir.list();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item_music,musicList);
        ListView lv = findViewById(R.id.lv_music_list);
        lv.setAdapter(new MusicAdapter(this,musicData));
        lv.setOnItemClickListener(this);
    }
    //初始化控件
    private void initViewContent(){
        player = new MediaPlayer();
        ibPlayOrPause = findViewById(R.id.ib_play_or_pause);
        ibPlayOrPause.setOnClickListener(this); //给暂停按钮设置监听
        tvMusicTitlle = findViewById(R.id.tv_music_title);
    }
    private void play(){

        try {
            //1.重置播放器(reset方法)
            player.reset();
            //2.设置数据源
            player.setDataSource(dir.getAbsolutePath()+"/"+musicData[musicIndex]);
            //3.缓冲
            player.prepare();
            //跳转到暂停位置的时间上
            player.seekTo(currenctTime);
            //4.播放
            player.start();
            //5.正在播放的歌曲标题
            tvMusicTitlle.setText(musicData[musicIndex]);
            //改变暂停按钮
            ibPlayOrPause.setImageResource(R.drawable.ic_music_note);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //暂停功能
    private void pause(){
        player.pause();
        //记录当前播放器播放的位置
        currenctTime = player.getCurrentPosition();
        ibPlayOrPause.setImageResource(R.drawable.ic_music_note);
    }
    //ListView被单机时执行的一个方法
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        musicIndex = position;
    }
    //实现暂停播放的功能
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_play_or_pause:
                if (player.isPlaying()){
                    pause();
                }else{
                    play();
                }
        }
    }
}
