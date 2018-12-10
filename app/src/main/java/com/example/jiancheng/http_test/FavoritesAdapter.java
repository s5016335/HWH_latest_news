package com.example.jiancheng.http_test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> implements View.OnClickListener {

    private List<Item> items;
    private  setOnitem setOnitem;

    public FavoritesAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_item,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date_item.setText(items.get(position).getDate());
        holder.title_item.setText(items.get(position).getTitle());
        holder.unit_item.setText(items.get(position).getUnit());

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void setSetOnitem(setOnitem setOnitem){
        this.setOnitem=setOnitem;
    }

    @Override
    public void onClick(View v) {

        setOnitem.onclick(v,(int)v.getTag());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_item,title_item,unit_item;
        public ViewHolder(View itemView) {
            super(itemView);

            date_item=(TextView)itemView. findViewById(R.id.Favorites_date_item);
            title_item=(TextView)itemView. findViewById(R.id.Favorites_title_item);
            unit_item=(TextView)itemView. findViewById(R.id.Favorites_unit_item);

        }
    }
}
