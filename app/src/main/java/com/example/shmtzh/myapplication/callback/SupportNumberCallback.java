package com.example.shmtzh.myapplication.callback;

import android.util.Log;

import com.example.shmtzh.myapplication.bus.EventBus;
import com.example.shmtzh.myapplication.event.InvalidCredentialsEvent;
import com.example.shmtzh.myapplication.event.ReceivedSupportNumberEvent;
import com.example.shmtzh.myapplication.model.SupportNumber;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shmtzh on 2/25/16.
 */
public class SupportNumberCallback extends AbstractCallback<SupportNumber> {
    private final EventBus mBus;
    private final String TAG = getClass().getSimpleName();

    public SupportNumberCallback(EventBus mBus) {
        this.mBus = mBus;
    }

    @Override
    public EventBus getBus() {
        return mBus;
    }

    @Override
    public boolean hasUserInteraction() {
        return true;
    }

    @Override
    public void success(final SupportNumber number, Response response) {
        final ReceivedSupportNumberEvent event = ReceivedSupportNumberEvent.getEvent(number);
        mBus.post(event);
    }

    @Override
    public void failure(RetrofitError e) {
        super.failure(e);
        Log.e(TAG, e.getMessage() + " error");
        final InvalidCredentialsEvent event = InvalidCredentialsEvent.getEvent();
        mBus.post(event);
    }

}
