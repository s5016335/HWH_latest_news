package com.example.jiancheng.http_test.View.Latest_news;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View view;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Request request ;

    public NewsPresenter(NewsContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void subscribe() {
        loadNews();
    }


    @Override
    public void loadNews() {

        request = new Request.Builder()
                .url(" https://www.gamer.com.tw/")
                .build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

               // Strutils strutils = new Strutils(response.body().string());
                //view.ShowNewList( strutils.getResultitem());

                String  strResponse= response.body().string();
                Log.d("Main",response.message());
            }
        });

    }



    @Override
    public void unsubscribe() {

    }
}
