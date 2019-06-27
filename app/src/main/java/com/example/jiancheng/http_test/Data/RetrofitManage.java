package com.example.jiancheng.http_test.Data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManage {
    private static  RetrofitManage retrofitManage =new RetrofitManage();
    private static Retrofit retrofit;
    WebApi webApi;

    private  RetrofitManage () {
        retrofit =new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webApi=retrofit.create(WebApi.class);
    }


    public static WebApi getWebApi() {
        return retrofitManage.webApi;
    }
}
