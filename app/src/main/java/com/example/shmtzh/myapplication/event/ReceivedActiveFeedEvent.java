package com.example.shmtzh.myapplication.event;

import com.example.shmtzh.myapplication.model.FeedModel;

/**
 * Created by shmtzh on 3/5/16.
 */
public class ReceivedActiveFeedEvent extends RemoteEvent {
    FeedModel model;

    public ReceivedActiveFeedEvent(FeedModel model) {
        this.model = model;
    }

    public static ReceivedActiveFeedEvent getEvent(FeedModel model) {
        return new ReceivedActiveFeedEvent(model);
    }

    public FeedModel getActiveFeed() {
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
