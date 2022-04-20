package com.example.weatherforecast.GSON;



import com.google.gson.annotations.SerializedName;

/**
 * @program: Basic
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-20 15:50
 **/
public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;
    public Update update;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
