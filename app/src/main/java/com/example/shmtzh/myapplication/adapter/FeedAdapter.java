package com.example.shmtzh.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.model.FeedModel;

import java.util.ArrayList;

/**
 * Created by shmtzh on 3/5/16.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private FeedModel list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView toName;
        public TextView fromName;
        public TextView index;
        public TextView toDistance;
        public TextView fromDistance;
        public TextView comments;
        public ViewHolder(View v) {
            super(v);
            toName = (TextView) v.findViewById(R.id.to_name);
            fromName = (TextView) v.findViewById(R.id.from_name);
            index = (TextView) v.findViewById(R.id.index);
            toDistance = (TextView) v.findViewById(R.id.to_distance);
            fromDistance = (TextView) v.findViewById(R.id.from_distance);
            comments = (TextView) v.findViewById(R.id.comments_text);
        }
    }

    public FeedAdapter(FeedModel list) {
        this.list = list;
    }

    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.toName.setText(list.getOrders().get(position).getToAddress());
        holder.fromName.setText(list.getOrders().get(position).getFromAddress());
        holder.index.setText(String.valueOf(list.getOrders().get(position).getCost()));
        holder.toDistance.setText(String.valueOf(list.getOrders().get(position).getDistanceTo()));
        holder.fromDistance.setText(list.getOrders().get(position).getDistance());
    }

    @Override
    public int getItemCount() {
        return list.getOrders().size();
    }
}