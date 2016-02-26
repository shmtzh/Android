package com.example.shmtzh.myapplication.event;

/**
 * Created by shmtzh on 2/25/16.
 */
public class UnexpectedRestErrorEvent extends RemoteEvent {
    private final boolean userInteraction;

    public UnexpectedRestErrorEvent(boolean userInteraction) {
        this.userInteraction = userInteraction;
    }

    public static UnexpectedRestErrorEvent getEvent(boolean userInteraction) {
        return new UnexpectedRestErrorEvent(userInteraction);
    }

    public boolean getUserInteraction() {
        return userInteraction;
    }
}
