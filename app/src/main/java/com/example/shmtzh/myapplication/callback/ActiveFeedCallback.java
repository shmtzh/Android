package com.example.shmtzh.myapplication.callback;

import android.util.Log;

import com.example.shmtzh.myapplication.bus.EventBus;
import com.example.shmtzh.myapplication.event.InvalidCredentialsEvent;
import com.example.shmtzh.myapplication.event.ReceivedActiveFeedEvent;
import com.example.shmtzh.myapplication.model.FeedModel;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shmtzh on 3/5/16.
 */
public class ActiveFeedCallback extends AbstractCallback<FeedModel> {
    private final EventBus mBus;
    private final String TAG = getClass().getSimpleName();

    public ActiveFeedCallback(EventBus mBus) {
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
    public void success(final FeedModel model, Response response) {
        final ReceivedActiveFeedEvent event = ReceivedActiveFeedEvent.getEvent(model);
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
