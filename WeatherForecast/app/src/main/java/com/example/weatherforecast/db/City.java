package com.example.weatherforecast.db;

import org.litepal.crud.LitePalSupport;

/**
 * @program: City
 * @description 市
 * @author: Cao Jingwei
 * @create: 2022-04-19 19:44
 **/
public class City extends LitePalSupport {
    private int id;
    private int provinceId;
    private String cityName;
    private int cityCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }
}
