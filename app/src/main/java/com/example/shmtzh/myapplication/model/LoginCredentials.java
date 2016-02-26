package com.example.shmtzh.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shmtzh on 2/26/16.
 */
public class LoginCredentials {
    @SerializedName("phone")
    private String phone;
    @SerializedName("password")
    private String password;
    @SerializedName("device_id")
    private String deviceId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
