package com.example.equipment_purchase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //声明的进度条和文本框
    private ProgressBar pbLife;
    private ProgressBar pbFirepower;
    private ProgressBar pbArmor;
    private TextView tvMainLife;
    private TextView tvMainFirepower;
    private TextView tvMainArmor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnShopEquip = findViewById(R.id.btn_shop);
        btnShopEquip.setOnClickListener(this);
        initProgress_Text();
    }
    //初始化进度条文本框
    protected void initProgress_Text(){
        pbLife = findViewById(R.id.pb_life);
        pbFirepower = findViewById(R.id.pb_firepower);
        pbArmor = findViewById(R.id.pb_armor);
        pbLife.setMax(1000);
        pbFirepower.setMax(1000);
        pbArmor.setMax(1000);
        tvMainLife = findViewById(R.id.tv_life_value);
        tvMainFirepower = findViewById(R.id.tv_firepower_value);
        tvMainArmor = findViewById(R.id.tv_armor_value);
    }
    public void onClick(View view) {
        Intent intent = new Intent(this,EquipActivity.class);
        startActivityForResult(intent,1);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1){
            if(resultCode == 1){
                ItemInfo itemInfo = (ItemInfo) data.getSerializableExtra("info");
                updateProgress(itemInfo);
            }
        }
    }
    //进度条更新
    protected void updateProgress(ItemInfo info){
        pbLife.setProgress(pbLife.getProgress()+info.getLife());
        pbFirepower.setProgress(pbFirepower.getProgress()+info.getFirepower());
        pbArmor.setProgress(pbArmor.getProgress()+info.getArmor());
        tvMainLife.setText(pbLife.getProgress()+"");
        tvMainFirepower.setText(pbFirepower.getProgress()+"");
        tvMainArmor.setText(pbArmor.getProgress()+"");
    }
}
