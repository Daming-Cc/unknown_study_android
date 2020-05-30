package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUserName;
    private EditText etPassWord;
    private EditText etAge;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private CheckBox cbLeopard1;
    private CheckBox cbE_100_WT;
    private CheckBox cbFv4005;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUserName = findViewById(R.id.et_uearname);
        etPassWord = findViewById(R.id.et_password);
        etAge = findViewById(R.id.et_age);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        cbLeopard1 = findViewById(R.id.hb_leopard1);
        cbE_100_WT = findViewById(R.id.hb_e_100_WT);
        cbFv4005 = findViewById(R.id.hb_fv4005);
    }

    public void click(View view) {
        Intent intent = new Intent(this,SubmitActivity.class);
        intent.putExtra("username",etUserName.getText().toString());
        intent.putExtra("password",etPassWord.getText().toString());
        intent.putExtra("age",Integer.parseInt(etAge.getText().toString()));
        String gender = " ";
        if (rbMale.isChecked()){
            gender = "男";
        }else if (rbFemale.isChecked()){
            gender = "女";
        }
        intent.putExtra("gender",gender);

        String hobby = " ";
        if (cbLeopard1.isChecked()){
            hobby += "豹一"+" ";
        }if (cbE_100_WT.isChecked()){
            hobby += "百运"+" ";
        }if (cbFv4005.isChecked()){
            hobby += "康威"+" ";
        }
        intent.putExtra("hobby",hobby);

        startActivity(intent);
    }
}
