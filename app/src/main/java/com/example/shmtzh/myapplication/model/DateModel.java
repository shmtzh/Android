package com.example.shmtzh.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shmtzh on 3/6/16.
 */
public class DateModel {
    @SerializedName("date")
    private String date;
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("timezone_type")
    private double timezoneType;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public double getTimezoneType() {
        return timezoneType;
    }

    public void setTimezoneType(double timezoneType) {
        this.timezoneType = timezoneType;
    }
}
