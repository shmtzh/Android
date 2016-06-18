package com.example.shmtzh.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by shmtzh on 3/5/16.
 */
public class FeedModel implements Serializable {
    @SerializedName("orders")
    ArrayList<OrderModel> orders = new ArrayList<>();
    @SerializedName("count")
    private int count;

    public ArrayList<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderModel> orders) {
        this.orders = orders;
    }
}
