package com.example.shmtzh.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shmtzh on 3/12/16.
 */
public class CompleteStatistic {
    @SerializedName("sum")
    private String sum;
    @SerializedName("deliveries")
    private int deliveries;
    @SerializedName("rating")
    private double rating;

    public CompleteStatistic(String sum, int deliveries, double rating) {
        this.sum = sum;
        this.deliveries = deliveries;
        this.rating = rating;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public int getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(int deliveries) {
        this.deliveries = deliveries;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
