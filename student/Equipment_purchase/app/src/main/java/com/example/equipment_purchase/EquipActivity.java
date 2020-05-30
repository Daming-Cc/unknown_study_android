package com.example.equipment_purchase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class EquipActivity extends AppCompatActivity implements View.OnClickListener{
    private ItemInfo swordInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);
        //创建双剑对象
        swordInfo = new ItemInfo(50,80,100);
        TextView tvSwordLife = findViewById(R.id.tv_equip_life);
        TextView tvSwordFirepower = findViewById(R.id.tv_equip_firepower);
        TextView tvSwordArmor = findViewById(R.id.tv_equip_armor);
        tvSwordLife.setText("生命值："+swordInfo.getLife()+"");
        tvSwordFirepower.setText("火力值："+swordInfo.getFirepower()+"");
        tvSwordArmor.setText("装甲值："+swordInfo.getArmor()+"");
        //ImageButton监听事件
        ImageButton ibSword = findViewById(R.id.ib_sword);
        ibSword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.ib_sword:
            intent.putExtra("info",swordInfo);
            break;
        }
        setResult(1,intent);
        finish();
    }
}
