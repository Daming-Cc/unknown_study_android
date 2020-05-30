package com.example.myapplication;


import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class LoginInformationSave {
    //文件数据保存
    public static void saveInformation (Context context,String username,String password){
        try {
            FileOutputStream fos = context.openFileOutput("logindata.txt",Context.MODE_PRIVATE);
            fos.write((username+"#"+password).getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //文件数据读取，并显示布局
    public static Map<String,String> getInformation(Context context) {
        try {
            FileInputStream fis = context.openFileInput("logindata.txt");
            byte[] buffer = new byte[fis.available()];//available方法：知道fis数组有多少字节
            fis.read(buffer);
            String content = new String(buffer);
            String[] loginInfo = content.split("#");//用户分割用户名和密码放在loginInfo里面
            Map<String, String> loginMap = new HashMap<String, String>();
            loginMap.put("username", loginInfo[0]);//Map中的格式：用户名在第一组数所以为0位
            loginMap.put("password", loginInfo[1]);
            fis.close();
            return loginMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
