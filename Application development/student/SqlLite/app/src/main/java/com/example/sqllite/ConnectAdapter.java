package com.example.sqllite;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ConnectAdapter extends BaseAdapter {
    private Context context;
    private List<PersonItem> datas;

    public ConnectAdapter(Context context,List<PersonItem> datas){
        this.context = context;
        this.datas = datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,R.layout.connect_item,null);
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvPhone = view.findViewById(R.id.tv_phone);
        tvName.setText(datas.get(position).getName());
        tvPhone.setText(datas.get(position).getPhone());
        return view;
    }
}
