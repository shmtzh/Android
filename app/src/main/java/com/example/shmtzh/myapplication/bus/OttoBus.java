package com.example.shmtzh.myapplication.bus;

import android.os.Handler;
import android.os.Looper;

import com.example.shmtzh.myapplication.event.AbstractEvent;
import com.squareup.otto.Bus;

/**
 * Created by shmtzh on 2/25/16.
 */
public class OttoBus implements EventBus {

    private final Bus ottoBus;

    public OttoBus() {
        ottoBus = new Bus();
    }

    @Override
    public void register(Object object) {
        ottoBus.register(object);
    }

    @Override
    public void unregister(Object object) {
        ottoBus.unregister(object);
    }

    @Override
    public void post(final AbstractEvent event) {
        ottoBus.post(event);
    }

    @Override
    public void postOnMainThread(final AbstractEvent event) {
        final Handler mHandlerCollectEnded = new Handler(Looper.getMainLooper());
        mHandlerCollectEnded.post(new Runnable() {
            @Override
            public void run() {
                ottoBus.post(event);
            }
        });
    }

    @Override
    public void postOnMainThread(final AbstractEvent event, final long delay) {
        final Handler mHandlerCollectEnded = new Handler(Looper.getMainLooper());
        mHandlerCollectEnded.postDelayed(new Runnable() {
            @Override
            public void run() {
                ottoBus.post(event);
            }
        }, delay);
    }


}
