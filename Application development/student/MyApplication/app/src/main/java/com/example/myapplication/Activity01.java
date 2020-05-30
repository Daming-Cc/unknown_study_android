//package com.example.myapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class Activity01 extends AppCompatActivity implements View.OnClickListener{
//    private Button btnOpen;
//    private EditText etWebSite;
//    private Button btnDial;
//    private EditText etPhoneNumber;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_01);
//        btnOpen = findViewById(R.id.btn_open);
//        btnOpen.setOnClickListener(this);
//        btnDial = findViewById(R.id.btn_dial);
//        btnDial.setOnClickListener(this);
//        etWebSite = findViewById(R.id.et_website);
//        etPhoneNumber = findViewById(R.id.et_dial);
//    }
//    @Override
//    //显示意图
////    public void onClick(View v) {
////        Intent intent = new Intent(this,Activity02.class);
////        startActivity(intent);
////    }
//    //隐式意图
////    public void onClick(View v) {
////        Intent intent = new Intent();
////        switch (v.getId()){
////            case R.id.btn_open:
////                String website = etWebSite.getText().toString();
////                intent.setAction(Intent.ACTION_VIEW);   //打开浏览器
////                intent.setData(Uri.parse(Uri.parse("http:"+"www.baidu.com"));
//////                intent.setData(Uri.parse(Uri.parse("http:"+ website));//设置打开的浏览器地址
////                break;
////            case R.id.btn_dial:
////                String phone = etWebSite.getText().toString();
////                intent.setAction(Intent.ACTION_VIEW);   //打开拨号
////                intent.setData(Uri.parse(Uri.parse("tel:"+ phone));//设置打开的电话号码
////                break;
////        }
//        startActivity(intent);
////        String website = etWebSite.getText().toString();
////        Intent intent = new Intent();
////        intent.setAction(Intent.ACTION_VIEW);   //打开浏览器
////        intent.setData(Uri.parse(Uri.parse("http:"+ website));
//////        intent.setData(Uri.parse("http://www.baidu.com")); //打开设置的数据网站
//    }
//}
