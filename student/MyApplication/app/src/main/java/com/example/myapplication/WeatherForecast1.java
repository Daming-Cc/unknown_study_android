package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecast1 extends AppCompatActivity implements View.OnClickListener {
    private WeatherInfo weatherInfo;    //定义一个WeatherInfo的名字为weatherInfo
    private List<WeatherInfo> weatherInfos; //定义一个WeatherInfo的集合list的名字为weatherInfos
    //建立所有控件
    private TextView tvCityName;
    private TextView tvWeather;
    private TextView tvWind;
    private TextView tvTemp;
    private TextView tvPm;
    private ImageView ivWeatherIcon;
    private Button btnBJ;
    private Button btnSH;
    private Button btnGZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast1);
        weatherService();
        initView();
        select_city_weather();
    }
    //处理下拉框中对应城市的天气
    private void select_city_weather() {
        //1、找到下拉框中的城市数据源(通过提取weatherInfos的大小来获取名称
        String [] cityName = new String[weatherInfos.size()];
        int i = 0;

        for (WeatherInfo info:weatherInfos){
            cityName[i] = info.getCityName();
            i++;
        }
        //2、建立适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item_city,cityName);
            //设置下拉资源中的样式(此方法为变大)
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //3、设置下拉框的适配器
        Spinner spCity = findViewById(R.id.sp_select_city);
        spCity.setAdapter(adapter);
        //4、下拉框的监听事件
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvTemp.setText(weatherInfos.get(position).getTemp());
                tvWind.setText(weatherInfos.get(position).getWind());
                tvWeather.setText(weatherInfos.get(position).getWeather());
                tvPm.setText(weatherInfos.get(position).getPm());
                String weather = weatherInfos.get(position).getWeather();
                if ("晴天".equals(weather)){
                    ivWeatherIcon.setImageResource(R.mipmap.sun);
                }else if ("晴天多云".equals(weather)){
                    ivWeatherIcon.setImageResource(R.mipmap.cloud_sun);
                }else if ("多云".equals(weather)){
                    ivWeatherIcon.setImageResource(R.mipmap.clouds);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //初始话控件
    private void initView() {
        tvCityName = findViewById(R.id.tv_city);
        tvWeather = findViewById(R.id.tv_weather);
        tvWind = findViewById(R.id.tv_wind);
        tvTemp = findViewById(R.id.tv_temp);
        tvPm = findViewById(R.id.tv_pm);
        ivWeatherIcon = findViewById(R.id.iv_weather);
        btnBJ = findViewById(R.id.btn_beijing);
        btnSH = findViewById(R.id.btn_shanghai);
        btnGZ = findViewById(R.id.btn_guangzhou);
        btnBJ.setOnClickListener(this);
        btnSH.setOnClickListener(this);
        btnGZ.setOnClickListener(this);
    }
    //用PULL解析器解析Xml文件
    private void weatherService(){
        try {
            //获取assets文件中的weather.xml文件中的流
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("assets/"+"weather.xml");
            //建议一个pull解析器
            XmlPullParser parser = Xml.newPullParser();
            //给解析器设置流和解码标准
            parser.setInput(is,"utf-8");
            //通过事件类型方法去获取解析器目前到达xml文件中的什么位置
            int type = parser.getEventType();
            //通过循环的方法来读取文档所有的内容
            weatherInfos = new ArrayList<WeatherInfo>();
            while (type != XmlPullParser.END_DOCUMENT){ //XmlPullParser.END_DOCUMENT表示文档的末尾
                switch (type){
                    case XmlPullParser.START_TAG:   //XmlPullParser.START_TAG   表示第一个标签的开头
                        if ("city".equals(parser.getName())){
                                weatherInfo = new WeatherInfo();
                        }else if ("temp".equals(parser.getName())){ //双引号中代表索引到文件中的开始标签，然后getName获取到标签值
                                String temp = parser.nextText();    //新建一个String格式的代号来获取到getName的下一个文本字符
    //                            Log.i("WeatherActivity",temp);
                                weatherInfo.setTemp(temp);
                        }else if ("wind".equals(parser.getName())){
                                String wind = parser.nextText();
                                weatherInfo.setWind(wind);
                        }else if ("name".equals(parser.getName())){
                                String name = parser.nextText();
                                weatherInfo.setCityName(name);
                        }else if ("weather".equals(parser.getName())){
                            String weather = parser.nextText();
                            weatherInfo.setWeather(weather);
                        }else if ("pm".equals(parser.getName())){
                                String pm = parser.nextText();
                                weatherInfo.setPm(pm);}
                        break;
                    case XmlPullParser.END_TAG:
                        if ("city".equals(parser.getName())){
                            weatherInfos.add(weatherInfo);
                        }
                        break;
                }
                type = parser.next();   //解析器往文档末尾走动的过程
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_beijing:
                updateWeatherService(0);
                break;
            case R.id.btn_shanghai:
                updateWeatherService(1);
                break;
            case R.id.btn_guangzhou:
                updateWeatherService(2);
                break;
        }
    }
    //更新布局上界面底部的天气的控件信息
    private void updateWeatherService(int position){
        tvCityName.setText(weatherInfos.get(position).getCityName());
        tvTemp.setText(weatherInfos.get(position).getTemp());
        tvWind.setText(weatherInfos.get(position).getWind());
        tvWeather.setText(weatherInfos.get(position).getWeather());
        tvPm.setText(weatherInfos.get(position).getPm());
        String weather = weatherInfos.get(position).getWeather();
        if ("晴天".equals(weather)){
            ivWeatherIcon.setImageResource(R.mipmap.sun);
        }else if ("晴天多云".equals(weather)){
            ivWeatherIcon.setImageResource(R.mipmap.cloud_sun);
        }else if ("多云".equals(weather)){
            ivWeatherIcon.setImageResource(R.mipmap.clouds);
        }
    }
}
