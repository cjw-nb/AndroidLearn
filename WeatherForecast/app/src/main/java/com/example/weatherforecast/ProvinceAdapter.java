package com.example.weatherforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherforecast.db.Province;

import java.util.List;

/**
 * @program: ProvinceAdapter
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-20 10:29
 **/
public class ProvinceAdapter extends ArrayAdapter<Province>{
    private int resourceId;

    public ProvinceAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Province> objects) {
        super(context,textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Province province = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView provinceName = (TextView) view.findViewById(R.id.province_item);
        provinceName.setText(province.getProvinceName());
        return view;
    }
}
