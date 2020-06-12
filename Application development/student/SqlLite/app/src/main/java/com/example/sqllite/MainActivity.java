package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    private TextView tvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new ConnectOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
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
        tvShow = findViewById(R.id.tv_show);
    }
    public void onClick(View v){
        SQLiteDatabase db;
        ContentValues values;
        switch (v.getId()){
            //插入记录的代码
            case R.id.btn_add:
                db = helper.getWritableDatabase();
                values = new ContentValues();
                values.put("name",etName.getText().toString());
                values.put("phone",etPhone.getText().toString());
                db.insert("connectperson",null,values);
                //db.exeSQL("insert into connectperson values()");
                db.close();
                Toast.makeText(this,"添加成功",Toast.LENGTH_LONG).show();
                etName.setText("");
                break;
            //查询记录的代码
            case R.id.btn_query:
                db = helper.getReadableDatabase();
                Cursor cursor = db.query("connectperson",null,null,null,null,null,null,null);
                //db.execSQL("select * from connectperson where name = ?",new Object[etName.getText().toString()]);
                if (cursor.getColumnCount()==0){
                    tvShow.setText("");
                }else {
                    cursor.moveToFirst();
                    tvShow.setText("姓名"+cursor.getString(0)+"电话"+cursor.getString(1));
                    while (cursor.moveToNext()){
                        //1.参考天气预报案例
                        //2.参与播放器Adapter - BaseAdapter
                        //3.lv.setAdapter(new)
                        tvShow.append("\n姓名"+cursor.getString(0)+"电话"+cursor.getString(1));
                    }
                }
                //ListView
                db.close();
                break;
/*            //查询记录的代码
            case R.id.btn_query:
                db = helper.getReadableDatabase();
                Cursor cursor = db.query("connectperson",null,null,null,null,null,null,null);
                //db.execSQL("select * from connectperson where name =?",new Object[]);
                if (cursor.getColumnCount()==0){
                    tvShow.setText("");
                }else{
                    cursor.moveToFirst();
                    tvShow.setText("姓名:"+cursor.getString(0)+"电话:"+cursor.getString(1));
                    while (cursor.moveToFirst()){
                        tvShow.append("\n姓名:"+cursor.getString(0)+"电话:"+cursor.getString(1));
                    }
                }
                db.close();
                break;*/
            //更新记录的代码
            case R.id.btn_update:
                db = helper.getWritableDatabase();
                values = new ContentValues();
                values.put("phone",etPhone.getText().toString());
                if (etName.getText().toString().isEmpty()){
                    db.update("connectperson",values,null,null);
                }else {
                    db.update("connectperson",values,"name=?",new String[]{etName.getText().toString()});
                }
                db.update("connectperson",values,"name=?",new String[]{etName.getText().toString()});
                db.close();
                Toast.makeText(this,"修改成功",Toast.LENGTH_LONG).show();
                break;
            //删除记录的代码
            case R.id.btn_delete:
                db  = helper.getWritableDatabase();
                if (etName.getText().toString().isEmpty()){
                    db.delete("connectperson",null,null);
                }else {
                    db.delete("connectperson", "name=?", new String[]{etName.getText().toString()});
                }
                db.close();
                Toast.makeText(this,"删除成功",Toast.LENGTH_LONG).show();
                break;

        }
    }
}
