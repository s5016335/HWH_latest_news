package com.example.jiancheng.http_test.utils;

import com.example.jiancheng.http_test.Data.Item;

import java.util.ArrayList;
import java.util.List;

public class Strutils {


    String response;
    String  date,title,unit,url;

    List<Item> items =new ArrayList<>();

    public Strutils(String response) {
        this.response = response;
        format();

    }

    public void format(){

        String webcontent = response;

        //擷取自訂開始和結束的部分
        int start = webcontent.indexOf("<table class=\"baseTB listTB list_TABLE hasBD hasTH\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" summary=\"\">");
        int end =webcontent.indexOf("</tbody>",start);

        webcontent=webcontent.substring(start,end);
        webcontent.trim();

        String data1=webcontent;

        int start2=data1.indexOf("<tbody>");

        data1=data1.substring(start2);
        data1.trim();

        String posts[]=data1.split("</tr>");

        strToList(posts);

    }

    public void strToList(String [] posts){
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

    }

    public List<Item> getResultitem(){

        return items;
    }

}
