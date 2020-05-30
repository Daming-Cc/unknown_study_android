package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class extends_baseadapter extends Weather_BaseAdapter {
    private Context context;
    private String[] phone;
    private int[] price;
    private int[] icon;

    public extends_baseadapter(Context context, String[] phone, int[] price, int[] icon) {
        this.context = context;
        this.phone = phone;
        this.price = price;
        this.icon = icon;
    }
    //可以返回数据源的条目数
    @Override
    public int getCount() {
        return phone.length;
    }
    //返回view对象
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,R.layout.baseadapter_item,null);
        TextView tvName = view.findViewById(R.id.tv_phone_name);
        tvName.setText(phone[position]);
        TextView tvPrice = view.findViewById(R.id.tv_phone_price);
        tvPrice.setText(price[position]+"");
        ImageView ivIcon = view.findViewById(R.id.iv);
        ivIcon.setImageResource(icon[position]);
        return view;
    }
    //返回条目内容
    @Override
    public Object getItem(int position) {
        return null;
    }
    //返回条目的ID
    @Override
    public long getItemId(int position) {
        return 0;
    }

}
