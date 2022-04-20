package com.example.weatherforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherforecast.db.City;
import com.example.weatherforecast.db.County;
import com.example.weatherforecast.db.Province;

import org.litepal.crud.LitePalSupport;

import java.util.List;

/**
 * @program: ProvinceAdapter
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-20 10:29
 **/
public class mAdapter extends ArrayAdapter{
    private int resourceId;

    public mAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<? extends LitePalSupport> objects) {
        super(context,textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        switch(resourceId){
            case R.layout.province_layout:
                Province province = (Province) getItem(position);
                TextView provinceName = (TextView) view.findViewById(R.id.province_item);
                provinceName.setText(province.getProvinceName());
                break;
            case R.layout.cjty_layout:
                City city = (City) getItem(position);
                TextView cityName = (TextView) view.findViewById(R.id.city_item);
                cityName.setText(city.getCityName());
                break;
            case R.layout.county_layout:
                County county = (County) getItem(position);
                TextView countyName = (TextView) view.findViewById(R.id.county_item);
                countyName.setText(county.getCountyName());
                break;
        }
        return view;
    }
}
