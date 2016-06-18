package com.example.shmtzh.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shmtzh on 3/6/16.
 */
public class OrderModel {
    @SerializedName("size")
    private String size;
    @SerializedName("receiver")
    private String receiver;
    @SerializedName("phone")
    private String phone;
    @SerializedName("from_address")
    private String fromAddress;
    @SerializedName("from_lon")
    private double fromLon;
    @SerializedName("from_lat")
    private double fromLat;
    @SerializedName("to_address")
    private String toAddress;
    @SerializedName("to_lon")
    private double toLon;
    @SerializedName("to_lat")
    private double toLat;
//    @SerializedName("courier")
//    private String courier;
    @SerializedName("code")
    private long code;
    @SerializedName("date_picked_up")
    private String datePickedUp;
    @SerializedName("date_delivered")
    private String dateDelivered;
    @SerializedName("distance")
    private String distance;
    @SerializedName("cost")
    private double cost;
    @SerializedName("id")
    private String id;
    @SerializedName("active")
    private boolean active;
    @SerializedName("status")
    private String status;
    @SerializedName("date_created")
    private String dateCreated;
    @SerializedName("date_updated")
    private String dateUpdated;
    @SerializedName("distance_to")
    private double distanceTo;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public double getFromLon() {
        return fromLon;
    }

    public void setFromLon(double fromLon) {
        this.fromLon = fromLon;
    }

    public double getFromLat() {
        return fromLat;
    }

    public void setFromLat(double fromLat) {
        this.fromLat = fromLat;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public double getToLon() {
        return toLon;
    }

    public void setToLon(double toLon) {
        this.toLon = toLon;
    }

    public double getToLat() {
        return toLat;
    }

    public void setToLat(double toLat) {
        this.toLat = toLat;
    }

//    public String getCourier() {
//        return courier;
//    }
//
//    public void setCourier(String courier) {
//        this.courier = courier;
//    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getDatePickedUp() {
        return datePickedUp;
    }

    public void setDatePickedUp(String datePickedUp) {
        this.datePickedUp = datePickedUp;
    }

    public String getDateDelivered() {
        return dateDelivered;
    }

    public void setDateDelivered(String dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public double getDistanceTo() {
        return distanceTo;
    }

    public void setDistanceTo(double distanceTo) {
        this.distanceTo = distanceTo;
    }
}
