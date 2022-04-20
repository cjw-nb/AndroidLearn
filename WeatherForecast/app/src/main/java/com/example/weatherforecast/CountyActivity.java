package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.weatherforecast.db.City;
import com.example.weatherforecast.db.County;
import com.example.weatherforecast.util.HttpUtil;
import com.example.weatherforecast.util.WeatherDataUtil;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CountyActivity extends AppCompatActivity {
    private List<County> countyList = new ArrayList<>();
    private int provinceId;
    private int cityId;
    private mAdapter mAdapter = null;
    private ListView listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_county);
        getSupportActionBar().hide();
        Intent intent =getIntent();
        provinceId = intent.getIntExtra("provinceId",0);
        cityId = intent.getIntExtra("cityId",0);
        queryCounty(provinceId,cityId);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String weatherId = countyList.get(i).geWeatherId();
                Intent intent = new Intent(CountyActivity.this,WeatherActivity.class);
                intent.putExtra("weather_id",weatherId);
                startActivity(intent);
            }
        });
    }

    private void queryCounty(int provinceId, int cityId) {
        countyList = LitePal.where("cityId = ?", cityId + "").find(County.class);
        if (countyList.size() == 0) {
            String address = "http://guolin.tech/api/china/" + provinceId +"/"+cityId;
            HttpUtil.sendOkHttpRequest(address, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    if (WeatherDataUtil.handleCountyRespoense(responseText, cityId)) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        queryCounty(provinceId,cityId);
                                    }
                                });
                            }
                        }).start();
                    }
                }
            });
        }
        mAdapter = new mAdapter(this, R.layout.county_layout, countyList);
        listView = (ListView) findViewById(R.id.county_list);
        listView.setAdapter(mAdapter);
    }
}