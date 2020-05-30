package com.example.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ConnectOpenHelper extends SQLiteOpenHelper {

    private Context context;
    public ConnectOpenHelper(@Nullable Context context) {
        super(context,"connect.db",null,1);
        this.context = context;
    }
    //数据库第一次创建时调用的方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        //放置第一个数据库版本所需要建立的表结构
        db.execSQL("create table connectperson(name varchar(20),phone varchar(11))");
        Toast.makeText(context,"第一次数据库创建了",Toast.LENGTH_LONG).show();
    }

    //数据库版本号增加时调用的方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //表结构发生改变时放置的代码
        Toast.makeText(context,"版本号被更新了",Toast.LENGTH_LONG).show();

    }
}
