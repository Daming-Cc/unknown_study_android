package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //声明布局中的各个控件
    private EditText etName;
    private EditText etPhone;
    private Button btnAdd;
    private Button btnQuety;
    private Button btnUpdate;
    private Button btnDelete;
    private ConnectOpenHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new ConnectOpenHelper(this);
        SQLiteDatabase DB = helper.getWritableDatabase();
        initView();
    }
    private void initView(){
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        btnAdd = findViewById(R.id.btn_add);
        btnQuety = findViewById(R.id.btn_query);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        btnAdd.setOnClickListener(this);
        btnQuety.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

    }
    public void onClick(View v){
        switch (v.getId()){
            //插入记录的代码
            case R.id.btn_add:
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name",etName.getText().toString());
                db.insert("connectperson",null,values);
                db.close();
                Toast.makeText(this,"添加成功",Toast.LENGTH_LONG).show();
                break;
            //查询记录的代码
            case R.id.btn_query:
                break;
            //更新记录的代码
            case R.id.btn_update:
                break;
            //删除记录的代码
            case R.id.btn_delete:
                break;

        }
    }
}
