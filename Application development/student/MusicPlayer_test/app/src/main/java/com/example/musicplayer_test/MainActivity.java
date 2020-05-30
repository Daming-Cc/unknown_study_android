package com.example.musicplayer_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private MediaPlayer player;
    private File dir;
    private String[] data;
    private int musicIndex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMusicData();
        initViewContent();
    }
    private void getMusicData(){
        new PermisionUtils().verifyStoragePermissions(this);
        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        data = dir.list();
//        String [] data = {"01","02"};
        ListView lv = findViewById(R.id.lv_music_list);
        lv.setAdapter(new MusicAdapter(data, this));
        lv.setOnItemClickListener(this);
    }
    private void initViewContent(){
        player = new MediaPlayer();
    }
    protected void play() {
        try {
            //重置播放器
            player.reset();
            //设置数据源
            player.setDataSource(dir.getAbsolutePath() + "/" + data[musicIndex]);
            //设置缓冲
            player.prepare();
            //播放
            player.start();
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
