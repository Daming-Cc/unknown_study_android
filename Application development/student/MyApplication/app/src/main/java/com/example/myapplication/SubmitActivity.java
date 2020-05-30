package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Intent intent = getIntent();
        TextView tvSubUserName = findViewById(R.id.tv_sub_username);
        TextView tvSubPassword = findViewById(R.id.tv_sub_password);
        TextView tvSubSubAge = findViewById(R.id.tv_sub_age);
        TextView tvSubGender = findViewById(R.id.tv_sub_gender);
        TextView tvSubHobby = findViewById(R.id.tv_sub_hobby);


        tvSubUserName.setText("姓    名:"+intent.getStringExtra("username"));
        tvSubPassword.setText("密    码:"+intent.getStringExtra("password"));
        tvSubSubAge.setText("年    龄:"+intent.getIntExtra("age",0));
        tvSubGender.setText("性    别:"+intent.getStringExtra("gender"));
        tvSubHobby.setText("爱    好:"+intent.getStringExtra("hobby"));

    }
}
