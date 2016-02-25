package com.example.shmtzh.myapplication.bus;

import com.example.shmtzh.myapplication.event.AbstractEvent;

/**
 * Created by shmtzh on 2/25/16.
 */
public interface EventBus {

    public void register(Object object);
    public void unregister(Object object);
    public void post(final AbstractEvent event);
    public void postOnMainThread(final AbstractEvent event);
    public void postOnMainThread(final AbstractEvent event, final long delay);

}
