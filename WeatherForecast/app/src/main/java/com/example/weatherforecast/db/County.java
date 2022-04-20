package com.example.weatherforecast.db;

import org.litepal.crud.LitePalSupport;

/**
 * @program: County
 * @description 县
 * @author: Cao Jingwei
 * @create: 2022-04-19 19:46
 **/
public class County extends LitePalSupport{
    private int id;
    private int cityId;
    private String countyName;
    private int countyCode;
    private String weatherId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(int countyCode) {
        this.countyCode = countyCode;
    }

    public String geWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }
}
