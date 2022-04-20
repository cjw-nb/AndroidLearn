package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.weatherforecast.db.Province;
import com.example.weatherforecast.util.query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Province> provinceList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        provinceList = query.queryProvinces();
        ProvinceAdapter provinceAdapter = new ProvinceAdapter(this,R.layout.province_layout,provinceList);
        ListView listView = (ListView) findViewById(R.id.province_list);
        listView.setAdapter(provinceAdapter);
    }
}