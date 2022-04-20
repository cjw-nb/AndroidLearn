package com.example.weatherforecast.util;

import com.example.weatherforecast.db.Province;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.internal.Util;

/**
 * @program: query
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-20 10:45
 **/
public class query {
    private static Province selectProvince;
    private static Province selectCity;
    private static List<Province> provinceList;
    public static List<Province> queryProvinces() {
        provinceList = LitePal.findAll(Province.class);
        if (provinceList.size() > 0) return provinceList;
        else {
            String address = "http://guolin.tech/api/china";
            if(queryFromServer(address, "province"))
                provinceList = queryProvinces();
        }
        return provinceList;
    }

    private static boolean queryFromServer(String address, final String type) {
        final boolean[] result = {false};
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                if ("province".equals(type))
                    result[0] = WeatherDataUtil.handleProvinceRespoense(responseText);
                else if ("city".equals(type))
                    result[0] = WeatherDataUtil.handleCityRespoense(responseText,selectProvince.getId());
                else result[0] = WeatherDataUtil.handleCountyRespoense(responseText,selectCity.getId());

            }

        });
        return result[0];
    }
}
