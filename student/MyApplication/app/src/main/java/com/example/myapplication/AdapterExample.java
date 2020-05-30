package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AdapterExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_example);
        //适配器-手机充电线
        //ListView-手机
        //data数据源-相当于电源
        //1、建议数据源
        String [] data = {"华为","三星","小米","苹果","联想"};
        //2、建立一个适配器(相当于购买充电线)ArrayAdapter/BaseAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item_adapter,data);
        //3、设置适配器到ListView上
        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);

    }
}
