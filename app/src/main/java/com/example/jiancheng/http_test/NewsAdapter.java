package com.example.jiancheng.http_test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jiancheng on 2018/3/21.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements  View.OnClickListener {

    private List<Item> items;
    private setOnitem setOnitem;

    public NewsAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.ViewHolder holder, final int position) {
        holder.date_item.setText(items.get(position).getDate());
        holder.title_item.setText(items.get(position).getTitle());
        holder.unit_item.setText(items.get(position).getUnit());

        //防止 item 愛心重複
        holder.love.setImageResource(R.drawable.ic_favorite_border_black_24dp);

        if (MainActivity.database.islove(items.get(position).getTitle())){
            holder.love.setImageResource(R.drawable.ic_favorite_black_24dp);
        }



        holder.love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判斷回傳值等於false加入
              if (!MainActivity.database.islove(items.get(position).getTitle())){
                  MainActivity.database.addlove(items.get(position).getDate(),items.get(position).getTitle(),items.get(position).getUnit(),items.get(position).getUrl());
                  holder.love.setImageResource(R.drawable.ic_favorite_black_24dp);
              }
              else {
                  MainActivity.database.removelove(items.get(position).getTitle());
                  holder.love.setImageResource(R.drawable.ic_favorite_border_black_24dp);
              }

            }
        });
        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  void  setSetOnitem(setOnitem setOnitem){
        this.setOnitem=setOnitem;
    }

    @Override
    public void onClick(View v) {
       setOnitem.onclick(v,(int)v.getTag());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date_item,title_item,unit_item;
        ImageView love;
        public ViewHolder(View itemView) {
            super(itemView);
            date_item=(TextView)itemView. findViewById(R.id.date_item);
            title_item=(TextView)itemView. findViewById(R.id.title_item);
            unit_item=(TextView)itemView. findViewById(R.id.unit_item);
            love=(ImageView)itemView. findViewById(R.id.love);

        }
    }
}
