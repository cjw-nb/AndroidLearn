package com.example.weatherforecast.GSON;

import com.google.gson.annotations.SerializedName;

/**
 * @program: NOW
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-20 15:58
 **/
public class Now {
    @SerializedName("tmp")
    public String tmeperature;
    @SerializedName("cond")
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
