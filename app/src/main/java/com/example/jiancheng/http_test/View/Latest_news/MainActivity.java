package com.example.jiancheng.http_test.View.Latest_news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jiancheng.http_test.Data.Item;
import com.example.jiancheng.http_test.R;
import com.example.jiancheng.http_test.View.Favorites.FavoritesList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements NewsContract.View{

    List<Item> items=new  ArrayList<Item>();
    RecyclerView recycler;
    NewsAdapter newsAdapter ;
    NewsContract.Presenter mpresenter;

    private OkHttpClient okHttpClient = new OkHttpClient();
    private Request request ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler=(RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);

        new NewsPresenter(this).subscribe();

        request = new Request.Builder()
                .url("http://www.hwh.edu.tw/files/501-1000-1000-1.php?Lang=zh-tw")
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
                Log.d("Main",strResponse);
            }
        });
 /*
        newsAdapter.setSetOnitem(new setOnitem() {
            @Override
            public void onclick(View view, int position) {
                Intent it = new Intent(MainActivity.this, webview.class);
                it.putExtra("url",items.get(position).getUrl());
                startActivity(it);
            }
        });

        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.title_love:
                Intent it = new Intent(MainActivity.this, FavoritesList.class);
                startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(NewsContract.Presenter Presenter) {

        mpresenter=Presenter;
    }

    @Override
    public void ShowNewList(List<Item> itemList) {
       // Log.d("2", String.valueOf(itemList));
        newsAdapter = new NewsAdapter(itemList);
        recycler.setAdapter(newsAdapter);
    }
}
