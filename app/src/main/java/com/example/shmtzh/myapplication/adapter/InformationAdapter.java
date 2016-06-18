package com.example.shmtzh.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.model.FeedModel;

import java.util.ArrayList;

/**
 * Created by shmtzh on 3/20/16.
 */
public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {
    ArrayList<String> list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
        }
    }

    public InformationAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public InformationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_information, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
