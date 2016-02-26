package com.example.shmtzh.myapplication.event;

import com.example.shmtzh.myapplication.model.LoginModel;

/**
 * Created by shmtzh on 2/26/16.
 */
public class ReceivedLoginEvent extends RemoteEvent {
    LoginModel model;

    public ReceivedLoginEvent(LoginModel model) {
        this.model = model;
    }

    public static ReceivedLoginEvent getEvent(LoginModel model) {
        return new ReceivedLoginEvent(model);
    }

    public LoginModel getLogin() {
        return model;
    }


    @Override
    public boolean equals(final Object object) {
        return object instanceof ReceivedLoginEvent;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "ReceivedLoginEvent";
    }
}
