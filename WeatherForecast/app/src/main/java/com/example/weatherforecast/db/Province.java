package com.example.weatherforecast.db;

import org.litepal.crud.LitePalSupport;

/**
 * @program: Province
 * @description 省
 * @author: Cao Jingwei
 * @create: 2022-04-19 19:43
 **/
public class Province extends LitePalSupport {
    private int id;
    private String provinceName;
    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
