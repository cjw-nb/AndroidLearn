package com.example.weatherforecast.GSON;

/**
 * @program: AQI
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-20 15:57
 **/
public class AQI {
    public AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
