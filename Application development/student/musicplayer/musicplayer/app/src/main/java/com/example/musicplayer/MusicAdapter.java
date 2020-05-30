package com.example.musicplayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class MusicAdapter extends BaseAdapter {
    private Context context;
    private String [] musicData;

    public MusicAdapter(Context context, String[] musicData) {
        this.context = context;
        this.musicData = musicData;
    }

    @Override
    public int getCount() {
        return musicData.length;
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
        View view = View.inflate(context,R.layout.musiclist,null);
        TextView tv = view.findViewById(R.id.tvmusicitem);
        tv.setText(musicData[position]);
        return view;
    }
}
