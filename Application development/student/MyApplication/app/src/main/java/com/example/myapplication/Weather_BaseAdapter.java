package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public abstract class Weather_BaseAdapter extends AppCompatActivity {
    private String [] phone;
    private int [] price;
    private int []icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather__base_adapter);
        phone = new String[]{"华为","小米","苹果"};
        price = new int[]{4000,5000,6000};
        icon = new int[]{R.mipmap.app,R.mipmap.game,R.mipmap.qq};
        ListView listView = findViewById(R.id.lv);
        listView.setAdapter((ListAdapter) new extends_baseadapter(this,phone,price,icon));
    }

    //可以返回数据源的条目数
    public abstract int getCount();

    //返回view对象
    public abstract View getView(int position, View convertView, ViewGroup parent);

    //返回条目内容
    public abstract Object getItem(int position);

    //返回条目的ID
    public abstract long getItemId(int position);
//    //内部类-->优点(也是当前类的一个成员(该类所以成员它都可用)：只适合当前类使用)
//    private class NewAdapter extends BaseAdapter {
//        @Override //返回数据源的条目数
//        public int getCount() { return phone.length;}
//        //返回view对象
//        public View getView(int position, View convertView, ViewGroup parent){
//            View view = View.inflate(Weather_BaseAdapter.this,R.layout.baseadapter_item,null);
//            TextView tvName = view.findViewById(R.id.tv_phone_name);
//            tvName.setText(phone[position]);
//            TextView tvPrice = view.findViewById(R.id.tv_phone_price);
//            tvPrice.setText(price[position]+"");
//            ImageView ivicon = view.findViewById(R.id.iv);
//            ivicon.setImageResource(icon[position]);
//            return view;
//        }
//        @Override
//        public Object getItem(int position) { return null; }
//        @Override
//        public long getItemId(int position) { return 0; }
//
//    }
}
