package com.example.jiancheng.http_test;

/**
 * Created by jiancheng on 2018/3/21.
 */

public class Item {

    private String date,title,unit,url;

    public Item() {
    }

    public Item(String date, String title, String unit, String url) {
        this.date = date;
        this.title = title;
        this.unit = unit;
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
