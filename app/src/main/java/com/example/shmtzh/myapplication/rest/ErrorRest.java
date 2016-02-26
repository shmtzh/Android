package com.example.shmtzh.myapplication.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shmtzh on 2/25/16.
 */
public class ErrorRest {

    @SerializedName("resource")
    String resource;
    @SerializedName("field")
    String field;
    @SerializedName("code")
    String code;

    public ErrorRest(String resource, String field, String code) {
        this.resource = resource;
        this.field = field;
        this.code = code;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
