package com.example.shmtzh.myapplication.event;

import com.example.shmtzh.myapplication.model.SupportNumber;

/**
 * Created by shmtzh on 2/25/16.
 */
public class ReceivedSupportNumberEvent extends RemoteEvent  {
    SupportNumber number;

    public ReceivedSupportNumberEvent(SupportNumber number) {
        this.number = number;
    }

    public static ReceivedSupportNumberEvent getEvent(SupportNumber user) {
        return new ReceivedSupportNumberEvent(user);
    }

    public SupportNumber getSupportNumber() {
        return number;
    }


    @Override
    public boolean equals(final Object object) {
        return object instanceof ReceivedSupportNumberEvent;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "ReceivedLoginEvent";
    }
}
