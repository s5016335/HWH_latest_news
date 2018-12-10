package com.example.jiancheng.http_test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void create(String sql){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    void addlove( String date, String title, String unit, String url){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String add = String.format("INSERT INTO lovelist (date ,title ,unit ,url)VALUES('%s','%s','%s','%s');",date,title,unit,url);
        sqLiteDatabase.execSQL(add);
    }
    void removelove(String title){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String remove = String.format("DELETE FROM lovelist WHERE title ='%s';",title);
        sqLiteDatabase.execSQL(remove);
    }

    boolean islove(String title){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String check = String.format("Select * FROM lovelist WHERE title ='%s';",title);
        Cursor cursor = sqLiteDatabase.rawQuery(check,null);

        if (cursor.getCount()<=0){

            cursor.close();
            return  false;
            //如果沒有回傳false
        }
        cursor.close();

        return true;
    }


    List<Item> getData(){

        List<Item>items=new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String check = "Select * FROM lovelist";
        Cursor cursor = sqLiteDatabase.rawQuery(check,null);

        while (cursor.moveToNext()){
            String date=cursor.getString(0);
            String title=cursor.getString(1);
            String unit=cursor.getString(2);
            String url=cursor.getString(3);

            items.add(new Item(date,title,unit,url));

        }


        return items;

    }
}
