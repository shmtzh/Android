package com.example.shmtzh.myapplication.event;

import android.util.Log;

/**
 * Created by shmtzh on 2/25/16.
 */
public class InvalidCredentialsEvent extends RemoteEvent {
    public InvalidCredentialsEvent() {
    }

    public static InvalidCredentialsEvent getEvent() {
        Log.d("event", "new event");

        return new InvalidCredentialsEvent();
    }
}
