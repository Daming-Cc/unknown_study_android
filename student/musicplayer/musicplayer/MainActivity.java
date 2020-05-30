package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private MediaPlayer player;//声明播放器
    private File dir;          //获取SD卡的路径
    private String []musicData; //歌曲列表
    private int musicIndex;    //歌曲位置
    private ListView lv;       //列表
    private ImageButton ibPlayOrPause; //播放和暂停按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMusicData();  // 用于获取音乐数据源
        initContent();  //用于初始化对象
    }
    protected void getMusicData(){
        PermisionUtils permisionUtils = new PermisionUtils();
        permisionUtils.verifyStoragePermissions(this);
//        new PermisionUtils().verifyStoragePermissions(this);
        dir = Environment.
                getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_MUSIC);
        musicData = dir.list();
        lv = findViewById(R.id.lv_music_list);
        lv.setAdapter(new MusicAdapter(this,musicData));
    }
    private void initContent(){
        //创建出一个新的播放器对象
        player = new MediaPlayer();
        lv.setOnItemClickListener(this);
        ibPlayOrPause = findViewById(R.id.ib_play_or_pause);
    }
    // 用于播放器的播放功能
    private void play(){
        try {
            //1.重置播放器
            player.reset();
            //2.设置数据源
            player.setDataSource(dir.getAbsolutePath()+"/"
                    +musicData[musicIndex]);
            //3.设置缓冲
            player.prepare();
            //4.播放
            player.start();
            //改变播放按钮
            ibPlayOrPause.setImageResource(android.
                    R.drawable.ic_media_pause);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        musicIndex = position;
        play();
    }

}
