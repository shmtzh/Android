package com.example.shmtzh.myapplication.event;

import com.example.shmtzh.myapplication.model.FeedModel;
import com.example.shmtzh.myapplication.model.LoginModel;

/**
 * Created by shmtzh on 3/5/16.
 */
public class ReceivedNewFeedEvent extends RemoteEvent {
    FeedModel model;

    public ReceivedNewFeedEvent(FeedModel model) {
        this.model = model;
    }

    public static ReceivedNewFeedEvent getEvent(FeedModel model) {
        return new ReceivedNewFeedEvent(model);
    }

    public FeedModel getNewFeed() {
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
