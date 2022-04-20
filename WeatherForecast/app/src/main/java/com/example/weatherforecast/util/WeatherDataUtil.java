package com.example.weatherforecast.util;

import android.text.TextUtils;

import com.example.weatherforecast.db.City;
import com.example.weatherforecast.db.County;
import com.example.weatherforecast.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @program: WaetherDataUtil
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-19 19:58
 **/
public class WeatherDataUtil {
    public static boolean handleProvinceRespoense(String response) {
        if (TextUtils.isEmpty(response) == false) {
            try {
                JSONArray provinces = new JSONArray(response);
                for (int i = 0; i < provinces.length(); i++) {
                    JSONObject provinceJson = provinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceJson.getString("name"));
                    province.setProvinceCode(provinceJson.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCityRespoense(String response,int provinceId){
        if (TextUtils.isEmpty(response) == false) {
            try {
                JSONArray citys = new JSONArray(response);
                for (int i = 0; i < citys.length(); i++) {
                    JSONObject cityJson = citys.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityJson.getString("name"));
                    city.setCityCode(cityJson.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCountyRespoense(String response,int cityId){
        if (TextUtils.isEmpty(response) == false) {
            try {
                JSONArray countys = new JSONArray(response);
                for (int i = 0; i < countys.length(); i++) {
                    JSONObject countyJson = countys.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyJson.getString("name"));
                    county.setCountyCode(countyJson.getInt("id"));
                    county.setCityId(cityId);
                    county.setWeatherId(countyJson.getString("weather_id"));
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
