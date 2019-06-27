package com.example.jiancheng.http_test.View.Latest_news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jiancheng.http_test.Data.Item;
import com.example.jiancheng.http_test.R;
import com.example.jiancheng.http_test.View.Favorites.FavoritesList;
import com.example.jiancheng.http_test.setOnItemClick;
import com.example.jiancheng.http_test.webview;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsContract.View{

    List<Item> items=new  ArrayList<Item>();
    RecyclerView recycler;
    NewsAdapter newsAdapter = new NewsAdapter();
    NewsContract.Presenter mpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler=(RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);

        new NewsPresenter(this).subscribe();



        newsAdapter.setSetOnItemClick(new setOnItemClick() {
            @Override
            public void onclick(View view, int position) {
                Intent it = new Intent(MainActivity.this, webview.class);
                it.putExtra("url",items.get(position).getUrl());
                startActivity(it);
            }
        });
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
        items=itemList;
        newsAdapter.setItems(itemList);
        recycler.setAdapter(newsAdapter);
    }
}
