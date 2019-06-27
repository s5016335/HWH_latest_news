package com.example.jiancheng.http_test.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebApi {
    @GET("news")
    Call<List<Item>> getNews();
}
