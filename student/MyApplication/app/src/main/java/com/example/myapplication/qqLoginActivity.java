package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Map;

public class qqLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUserName;
    private EditText etPassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_qqlogin);
        Button  btnLogin = findViewById(R.id.btn_qqlogin);
        btnLogin.setOnClickListener(this);
        /*打到布局中的用户名和密码*/
        etUserName = findViewById(R.id.et_uearname);
        etPassWord = findViewById(R.id.et_password);
        //用户名和密码的回显
        Map<String, String> info = LoginInformationSave.getInformation(this);
        if (info != null){
            etUserName.setText(info.get("username"));
            etPassWord.setText(info.get("password"));
        }
    }

    @Override
    public void onClick(View v) {
        String userName = etUserName.getText().toString();
        String passWord = etPassWord.getText().toString();
        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(passWord)){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        //用户名和密码保存到文件中
        LoginInformationSave.saveInformation(this,userName,passWord);

        //触发按钮提示
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
    }
}
