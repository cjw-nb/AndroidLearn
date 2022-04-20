package com.example.weatherforecast.GSON;

import com.google.gson.annotations.SerializedName;

/**
 * @program: Forecast
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-20 16:11
 **/
public class Forecast {
    public String date;

    @SerializedName("tmp")
    public Temperature temperature;
    @SerializedName("cond")
    public More more;

    public class Temperature{
        public String max;
        public String min;
    }
    public class More{
        @SerializedName("txt_d")
        public String info;
    }
}
