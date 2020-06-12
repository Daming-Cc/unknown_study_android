package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public abstract class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //声明布局中的各个控件
    private EditText etName;
    private EditText etPhone;
    private Button btnAdd;
    private Button btnQuety;
    private Button btnUpdate;
    private Button btnDelete;
    private ConnectOpenHelper helper;
    private ListView lvShow;
    private String [] name;
    private int [] phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new ConnectOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        initView();
        name = new String[]{"华为","小米","苹果"};
        phone = new int[]{4000,5000,6000};
        ListView listView = findViewById(R.id.lv_show);
        listView.setAdapter((ListAdapter) new extends_baseadapter(this,name,phone));

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
        lvShow = findViewById(R.id.lv_show);
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
                if (cursor.getColumnCount()==0){
                    lvShow.set
                }else {
                    cursor.moveToFirst();
                    tvShow.setText("姓名"+cursor.getString(0)+"电话"+cursor.getString(1));
                    while (cursor.moveToNext()){
                        //1.参考天气预报案例
                        //2.参与播放器Adapter - BaseAdapter
                        //3.lv.setAdapter(new)
                        tvShow.append("\n姓名"+cursor.getString(0)+"电话"+cursor.getString(1));
                    }
                db.close();
                break;
            //查询记录的代码
/*            case R.id.btn_query:
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
                break;*/
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

    //可以返回数据源的条目数
    public abstract int getCount();

    //返回view对象
    public abstract View getView(int position, View convertView, ViewGroup parent);

    //返回条目内容
    public abstract Object getItem(int position);

    //返回条目的ID
    public abstract long getItemId(int position);
}
