package com.example.weatherforecast.util;

import javax.xml.transform.OutputKeys;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @program: HttpUtil
 * @description 发起http请求
 * @author: Cao Jingwei
 * @create: 2022-04-19 19:53
 **/
public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
