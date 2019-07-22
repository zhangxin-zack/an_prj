package com.scorer.client.tools;

import com.scorer.client.aop.OKHttp_Log;
import com.google.gson.Gson;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;


public class HTTP_Tools {

    public String OK_PostParam_Sync(final String url, final Map<String, String> param) {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new OKHttp_Log()).build();
        FormBody.Builder formBody = new FormBody.Builder();
        if (param != null) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                formBody.add(entry.getKey(), entry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();
        try {
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <R> R OK_GetParam_Sync(String url, Map<String,String> param, Class<R> rClass) {
        R re;
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new OKHttp_Log()).build();
        if (param != null) {
            StringBuilder urlBuilder = new StringBuilder(url);
            for (Map.Entry<String, String> entry : param.entrySet()) {
                if(urlBuilder.toString().contains("?")){
                    urlBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }else{
                    urlBuilder.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
            url = urlBuilder.toString();
        }
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String returnContent = Objects.requireNonNull(response.body()).string();
            re = new Gson().fromJson(returnContent, rClass);
            return re;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
