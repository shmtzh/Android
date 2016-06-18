package com.example.shmtzh.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shmtzh on 3/12/16.
 */
public class RegularStatistic {
    @SerializedName("sum")
    private int sum;
    @SerializedName("deliveries")
    private int deliveries;
    @SerializedName("date_from_timestamp")
    private double DateFromTimestamp;
    @SerializedName("date_to_timestamp")
    private double DateToTimestamp;

    public RegularStatistic(int sum, int deliveries, double dateFromTimestamp, double dateToTimestamp) {
        this.sum = sum;
        this.deliveries = deliveries;
        DateFromTimestamp = dateFromTimestamp;
        DateToTimestamp = dateToTimestamp;
    }


    public void setSum(int sum) {
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }

    public int getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(int deliveries) {
        this.deliveries = deliveries;
    }

    public double getDateFromTimestamp() {
        return DateFromTimestamp;
    }

    public void setDateFromTimestamp(double dateFromTimestamp) {
        DateFromTimestamp = dateFromTimestamp;
    }

    public double getDateToTimestamp() {
        return DateToTimestamp;
    }

    public void setDateToTimestamp(double dateToTimestamp) {
        DateToTimestamp = dateToTimestamp;
    }
}
