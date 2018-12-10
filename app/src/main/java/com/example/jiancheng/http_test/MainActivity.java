package com.example.jiancheng.http_test;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Item> items=new  ArrayList<Item>();
    RecyclerView recycler;
    NewsAdapter newsAdapter ;
    public  static Database database;
    public  static  MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database =new Database(this,"LOVE.db",null,1);
        database.create("CREATE TABLE IF NOT EXISTS lovelist(date VARCHAR,title VARCHAR,unit VARCHAR,url VARCHAR)");

        recycler=(RecyclerView) findViewById(R.id.recycler);

        try {
            URL url = new URL("http://www.hwh.edu.tw/files/501-1000-1000-1.php?Lang=zh-tw");
            new AccessInternet().execute(url);
        } catch (MalformedURLException e) {

        }

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);

    }

    private class  AccessInternet extends AsyncTask<URL,Void,String>{

        BufferedReader reader = null;
        StringBuilder stringBuilder;
        @Override
        protected String doInBackground(URL... params) {

            try {
                HttpURLConnection connection = (HttpURLConnection)params[0].openConnection();

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                stringBuilder=new StringBuilder();

                String line = null;

                while ((line=reader.readLine())!=null){

                    stringBuilder.append(line + "\n");

                }

                return stringBuilder.toString();

            } catch (IOException e) {
                return e.toString();
            }
            
        }

        @Override
        protected void onPostExecute(String s) {
            String  date,title,unit,url;
            String webcontent = s;
            
            int start = webcontent.indexOf("<table class=\"baseTB listTB list_TABLE hasBD hasTH\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" summary=\"\">");
            int end =webcontent.indexOf("</tbody>",start);

            //擷取自訂開始和結束的部分
            webcontent=webcontent.substring(start,end);
            webcontent.trim();
            
            String data1=webcontent;

            int start2=data1.indexOf("<tbody>");

            data1=data1.substring(start2);
            data1.trim();

            String posts[]=data1.split("</tr>");
            String curpost;


          for (int i=0 ;i<posts.length-1;i++){
               curpost=posts[i];

               int local1=curpost.indexOf("nowrap");
               int local2=curpost.indexOf("title=");
               int local3=curpost.indexOf("href");
               int local4=curpost.indexOf("\">",local3);
               int local5=curpost.lastIndexOf("nowrap");
               int local6=curpost.lastIndexOf("td>");

               date=curpost.substring(local1+16,local1+32);
               date=date.trim();

               title=curpost.substring(local2+7,local3-3);
               title=title.trim();

               url=curpost.substring(local3+6,local4);
               url=url.trim();

               unit=curpost.substring(local5+8,local6-2);
               unit=unit.trim();

              items.add(new Item(date,title,unit,url));
             }
             newsAdapter = new NewsAdapter(items);

            recycler.setAdapter(newsAdapter);
            newsAdapter.setSetOnitem(new setOnitem() {
                @Override
                public void onclick(View view, int position) {
                    Intent it = new Intent(MainActivity.this,webview.class);
                    it.putExtra("url",items.get(position).getUrl());
                    startActivity(it);

                }
            });

        }
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
                Intent it = new Intent(MainActivity.this,FavoritesList.class);
                startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}
