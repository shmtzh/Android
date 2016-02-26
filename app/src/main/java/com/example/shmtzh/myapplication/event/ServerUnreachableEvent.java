package com.example.shmtzh.myapplication.event;

/**
 * Created by shmtzh on 2/25/16.
 */
public class ServerUnreachableEvent extends RemoteEvent {
    private final boolean userInteraction;

    public ServerUnreachableEvent(boolean userInteraction) {
        this.userInteraction = userInteraction;
    }

    public static ServerUnreachableEvent getEvent(boolean userInteraction) {
        return new ServerUnreachableEvent(userInteraction);
    }

    public boolean getUserInteraction() {
        return userInteraction;
    }
}
