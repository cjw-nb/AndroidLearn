package com.example.weatherforecast.GSON;

import com.google.gson.annotations.SerializedName;

/**
 * @program: Suggestion
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-20 16:02
 **/
public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;
    @SerializedName("cw")
    public CarWash carWash;
    public Sport sport;
    public class Comfort{
        @SerializedName("txt")
        public String info;
    }
    public class CarWash{
        @SerializedName("txt")
        public String info;
    }
    public class Sport{
        @SerializedName("txt")
        public String info;
    }
}
