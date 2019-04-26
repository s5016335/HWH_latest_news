package com.example.jiancheng.http_test.View.Favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jiancheng.http_test.Data.Item;
import com.example.jiancheng.http_test.R;
import com.example.jiancheng.http_test.setOnitem;
import com.example.jiancheng.http_test.webview;

import java.util.ArrayList;
import java.util.List;

public class FavoritesList extends AppCompatActivity {


    private List<Item> items=new ArrayList<>();


    RecyclerView recycler_favorites;
    FavoritesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);


        recycler_favorites=(RecyclerView) findViewById(R.id.recycler_favorites);

        recycler_favorites.setLayoutManager(new LinearLayoutManager(this));
        recycler_favorites.setHasFixedSize(true);
       // items= MainActivity.database.getData();

        adapter=new FavoritesAdapter(items);

        adapter.setSetOnitem(new setOnitem() {
            @Override
            public void onclick(View view, int position) {
                Intent it = new Intent(FavoritesList.this, webview.class);
                it.putExtra("url",items.get(position).getUrl());
                startActivity(it);
            }
        });

        recycler_favorites.setAdapter(adapter);


    }
}
