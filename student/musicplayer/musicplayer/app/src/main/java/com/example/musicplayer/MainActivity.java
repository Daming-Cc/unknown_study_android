package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private MediaPlayer player;//声明播放器
    private File dir;          //获取SD卡的路径
    private String []musicData; //歌曲列表
    private int musicIndex;    //歌曲位置-歌曲下标
    private ListView lv;       //列表
    private ImageButton ibPlayOrPause; //播放和暂停按钮
    private  TextView tvMusicTitle; //歌曲的标题
    private  int currenctTime; //当前歌曲暂停的时间
    private ImageButton ibNext;//下一首按钮
    private ImageButton ibProvious;//上一首按钮
    private boolean isRuning;//用于控制什么时间停止线程
    private UpdateHandler handler; //消息处理机制
    private ProgressBar pbProgress;//声明进度条
    private TextView tvCurrectPosition;//声明当前时间
    private TextView tvDuration;//声明歌曲总时间
    private SimpleDateFormat sdf;//声明时间日期格式

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMusicData();  // 调用获取音乐数据源
        initContent();  //调用初始化对象
        new UpdateProgressThread().start();

    }
    //获取音乐数据源
    protected void getMusicData(){
        PermisionUtils permisionUtils = new PermisionUtils();
        //6.0版本以上动态获取sd卡信息
        permisionUtils.verifyStoragePermissions(this);
        new PermisionUtils().verifyStoragePermissions(this);
        dir = Environment.
                getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_MUSIC);
        musicData = dir.list();
        lv = findViewById(R.id.lv_music_list);
        lv.setAdapter(new MusicAdapter(this,musicData));
        lv.setOnItemClickListener(this);//给布局添加单击事件
    }
    //初始化对象
    private void initContent(){
        //创建出一个新的播放器对象
        player = new MediaPlayer();
        ibPlayOrPause = findViewById(R.id.ib_play_or_pause);
        ibPlayOrPause.setOnClickListener(this);
        tvMusicTitle = findViewById(R.id.tv_music_title);
        ibNext = findViewById(R.id.ib_next);
        ibProvious = findViewById(R.id.ib_previous);
        ibNext.setOnClickListener(this);
        ibPlayOrPause.setOnClickListener(this);
        ibProvious.setOnClickListener(this);
        isRuning = true;
        handler = new UpdateHandler();
        pbProgress = findViewById(R.id.pb_music_gress);
        tvCurrectPosition = findViewById(R.id.tv_music_current_position);
        tvDuration = findViewById(R.id.tv_music_duration);
        sdf = new SimpleDateFormat("mm:ss", Locale.CHINA);
    }
    //ListView被单击时执行的一个方法　
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        musicIndex = position;//判断点击了，xml中的哪一个下标位置，从而知道那个位置上的歌曲信息
        play();//调用播放功能
    }
    // 用于播放器的   播放功能
    private void play(){
        try {
            //1.重置播放器
            player.reset();
            //2.设置数据源
            player.setDataSource(dir.getAbsolutePath()+"/"
                    +musicData[musicIndex]);//获取sd卡中音乐播放的绝对路径
            //3.设置缓冲
            player.prepare();
                //3.1跳转到当前时间后再播放
                player.seekTo(currenctTime);
            //4.播放
            player.start();
                //4.1正在播放歌曲的标题
                tvMusicTitle.setText(musicData[musicIndex]);
                //4.2改变播放按钮   图标变为暂停
                ibPlayOrPause.setImageResource(android.
                    R.drawable.ic_media_pause);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 用于播放器的   暂停功能
    private void pause(){
        player.pause();//暂停
        currenctTime = player.getCurrentPosition();//记住暂停的时间点
        //改变播放按钮   图标变为开始
        ibPlayOrPause.setImageResource(android.
                R.drawable.ic_media_play);
    }
    //播放到下一首歌曲
    private void nextmusic(){
        musicIndex++;
        if (musicIndex>musicData.length-1) {
            musicIndex = 0;
        }
        play();
    }
    //播放上一首歌曲
    private void previous(){
        musicIndex--;
        if (musicIndex<0){
            musicIndex = musicData.length-1;
        }
        play();
    }
    //当系统监听到播放按钮被单机后执行此方法
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_play_or_pause:
                if(player.isPlaying()){
                    pause();
                }else{
                    play();
                }
                break;
            case R.id.ib_next:
                nextmusic();
                break;
            case R.id.ib_previous:
                previous();
                break;
        }
    }
    //线程：建立一个私有的内部类:线程不能用于处理耗时操作
    private class UpdateProgressThread extends Thread{
        public void run(){
            super.run();
            while (isRuning){
                Message msg = new Message();
                //进行进度条的更新Handler
                handler.sendMessage(msg);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class UpdateHandler extends Handler {
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            int currectPosition = player.getCurrentPosition();
            int duration = player.getDuration();
            int progress = currectPosition*100/duration;
            pbProgress.setProgress(progress);
            tvCurrectPosition.setText(sdf.format(new Date(currectPosition)));
            tvDuration.setText(sdf.format(new Date(duration)));
        }
    }
    //更新进度条的Handler
}
