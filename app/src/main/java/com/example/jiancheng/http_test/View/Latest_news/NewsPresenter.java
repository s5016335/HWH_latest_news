package com.example.jiancheng.http_test.View.Latest_news;

import android.util.Log;

import com.example.jiancheng.http_test.Data.Item;
import com.example.jiancheng.http_test.Data.RetrofitManage;
import com.example.jiancheng.http_test.Data.WebApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresenter implements NewsContract.Presenter {
    private NewsContract.View view;
    //private OkHttpClient okHttpClient = new OkHttpClient();
    //private Request request ;
    WebApi webApi = RetrofitManage.getWebApi();

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

       webApi.getNews().enqueue(new Callback<List<Item>>() {
           @Override
           public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
               view.ShowNewList(response.body());
               Log.d("Main","ff"+response.body());
           }

           @Override
           public void onFailure(Call<List<Item>> call, Throwable t) {
               Log.d("Main","ff"+t.toString());
           }
       });

               // Strutils strutils = new Strutils(response.body().string());
                //view.ShowNewList( strutils.getResultitem());

    }



    @Override
    public void unsubscribe() {

    }


}
