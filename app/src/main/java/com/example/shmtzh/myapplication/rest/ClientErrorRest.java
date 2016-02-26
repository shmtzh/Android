package com.example.shmtzh.myapplication.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shmtzh on 2/25/16.
 */
public class ClientErrorRest {
    @SerializedName("message")
    private String message;

    @SerializedName("error")
    private ErrorRest error;

    public ClientErrorRest(String message, ErrorRest error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorRest getError() {
        return error;
    }

    public void setErrors(ErrorRest error) {
        this.error = error;
    }
}
