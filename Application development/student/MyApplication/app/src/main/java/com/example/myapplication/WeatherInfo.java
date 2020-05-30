package com.example.myapplication;

public class WeatherInfo{
    private String temp;
    private String weather;
    private String wind;
    private String cityName;
    private String pm;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "temp='" + temp + '\'' +
                ", weather='" + weather + '\'' +
                ", wind='" + wind + '\'' +
                ", cityName='" + cityName + '\'' +
                ", pm='" + pm + '\'' +
                '}';
    }
}

