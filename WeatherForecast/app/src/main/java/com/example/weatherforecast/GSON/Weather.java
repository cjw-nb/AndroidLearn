package com.example.weatherforecast.GSON;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @program: Weather
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-20 16:14
 **/
public class Weather {
    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
