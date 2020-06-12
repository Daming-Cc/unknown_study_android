package com.example.sqllite;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class extends_baseadapter extends MainActivity {
    private Context context;
    private String[] name;
    private int[] phone;

    public extends_baseadapter(Context context, String[] name,int []phone) {
        this.context = context;
        this.name = name;
        this.phone = phone;
    }
    //可以返回数据源的条目数
    @Override
    public int getCount() {
        return phone.length;
    }
    //返回view对象
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,R.layout.item,null);
        TextView tvName = view.findViewById(R.id.tv_name);
        tvName.setText(name[position]);
        TextView tvPhone = view.findViewById(R.id.tv_phone);
        tvPhone.setText(phone[position]+"");
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
