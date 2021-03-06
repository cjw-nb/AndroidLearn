package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weatherforecast.GSON.Forecast;
import com.example.weatherforecast.GSON.Weather;
import com.example.weatherforecast.util.HttpUtil;
import com.example.weatherforecast.util.WeatherDataUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    private ScrollView weatherLayout;
    private HorizontalScrollView forecastLayout;
    private TextView cityTitle;
    private TextView weatherUpdataTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private LinearLayout mforecastLayout;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;

    private ImageView picImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getSupportActionBar().hide();
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        cityTitle = (TextView) findViewById(R.id.title_city);
        weatherUpdataTime = (TextView) findViewById(R.id.title_update_time);
        degreeText = (TextView) findViewById(R.id.now_degree);
        weatherInfoText = (TextView) findViewById(R.id.now_info);
        mforecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        aqiText = (TextView) findViewById(R.id.aqi_text);
        pm25Text = (TextView) findViewById(R.id.pm25_text);
        comfortText = (TextView) findViewById(R.id.comfort_text);
        carWashText = (TextView) findViewById(R.id.car_wash_text);
        sportText = (TextView) findViewById(R.id.sport_text);
        forecastLayout = (HorizontalScrollView) findViewById(R.id.forecast_scroll);
        picImg = (ImageView) findViewById(R.id.pic_img);
        loadPic();
        String weatherId = getIntent().getStringExtra("weather_id");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString(weatherId, null);
        if (weatherString != null) {
            Weather weather = WeatherDataUtil.handleWeatherResponse(weatherString);
            showWeatherInfo(weather);
        } else {
            weatherLayout.setVisibility(View.VISIBLE);
            requestWeather(weatherId);
        }

    }

    private void loadPic() {
        String requestPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(picImg);
                    }
                });
            }
        });
    }

    private void showWeatherInfo(Weather weather) {
        String cityName = weather.basic.cityName;
        String updateTime = weather.basic.update.updateTime.split(" ")[1];
        String degree = weather.now.tmeperature + "???";
        String weatherInfo = weather.now.more.info;
        cityTitle.setText(cityName);
        weatherUpdataTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        mforecastLayout.removeAllViews();
        for (Forecast forecast : weather.forecastList) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            dateText.setText(forecast.date);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            infoText.setText(forecast.more.info);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            maxText.setText(forecast.temperature.max + "???");
            TextView minText = (TextView) view.findViewById(R.id.min_text);
            minText.setText(forecast.temperature.min + "???");
            mforecastLayout.addView(view);
        }
        if (weather.aqi != null) {
            aqiText.setText("aqi:" + weather.aqi.city.aqi);
            pm25Text.setText("pm2.5:" + weather.aqi.city.pm25);
        }
        String comfort = "?????????" + weather.suggestion.comfort.info;
        String carWash = "????????????" + weather.suggestion.carWash.info;
        String sport = "????????????" + weather.suggestion.sport.info;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);
    }

    private void requestWeather(String weatherId) {
        Log.d("12312", "requestWeather: " + weatherId);
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=bc0418b57b2d4918819d3974ac1285d9";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = WeatherDataUtil.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString(weatherId,responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}