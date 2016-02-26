package com.example.shmtzh.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shmtzh on 2/26/16.
 */
public class LoginModel {
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("logo")
    private String logo;
    @SerializedName("token")
    private String token;
//    @SerializedName("salt")
//    private String salt;
//    @SerializedName("password")
//    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

