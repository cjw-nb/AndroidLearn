package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.weatherforecast.db.Province;
import com.example.weatherforecast.util.HttpUtil;
import com.example.weatherforecast.util.WeatherDataUtil;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private List<Province> provinceList = new ArrayList<>();
    private mAdapter mAdapter = null;
    private ListView listView =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queryProvinces();
        getSupportActionBar().hide();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Province province = provinceList.get(i);
                Intent intent = new Intent(MainActivity.this,CityActivity.class);
                intent.putExtra("provinceId",province.getProvinceCode());
                startActivity(intent);
            }
        });
    }

    public void queryProvinces() {
        provinceList = LitePal.findAll(Province.class);
        if (provinceList.size() == 0) {
            String address = "http://guolin.tech/api/china";
            HttpUtil.sendOkHttpRequest(address, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    if (WeatherDataUtil.handleProvinceRespoense(responseText)) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        queryProvinces();
                                    }
                                });
                            }
                        }).start();
                    }
                }
            });
        }
        mAdapter = new mAdapter(this, R.layout.province_layout, provinceList);
        listView = (ListView) findViewById(R.id.province_list);
        listView.setAdapter(mAdapter);
    }
}