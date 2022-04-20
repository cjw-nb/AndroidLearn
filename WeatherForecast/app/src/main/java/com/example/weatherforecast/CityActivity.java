package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.weatherforecast.db.City;
import com.example.weatherforecast.util.HttpUtil;
import com.example.weatherforecast.util.WeatherDataUtil;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CityActivity extends AppCompatActivity {
    private List<City> cityList = new ArrayList<>();
    private int provinceId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        provinceId=intent.getIntExtra("provinceId",0);
        quertCity(provinceId);
    }

    private void quertCity(int provinceId) {
        cityList = LitePal.where("provinceId = ?",provinceId+"").find(City.class);
        if (cityList.size() == 0) {
            String address = "http://guolin.tech/api/china/"+provinceId;
            HttpUtil.sendOkHttpRequest(address, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    if (WeatherDataUtil.handleCityRespoense(responseText,provinceId)) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        quertCity(provinceId);
                                    }
                                });
                            }
                        }).start();
                    }
                }
            });
        }
        mAdapter mAdapter = new mAdapter(this, R.layout.cjty_layout, cityList);
        ListView listView = (ListView) findViewById(R.id.city_list);
        listView.setAdapter(mAdapter);
    }
}