package com.example.shmtzh.myapplication.callback;

import android.util.Log;

import com.example.shmtzh.myapplication.bus.EventBus;
import com.example.shmtzh.myapplication.event.InvalidCredentialsEvent;
import com.example.shmtzh.myapplication.event.ReceivedMonthStatisticEvent;
import com.example.shmtzh.myapplication.model.RegularStatistic;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shmtzh on 3/12/16.
 */
public class MonthStatisticCallback extends AbstractCallback<RegularStatistic> {
    private final EventBus mBus;
    private final String TAG = getClass().getSimpleName();

    public MonthStatisticCallback(EventBus mBus) {
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
    public void success(final RegularStatistic statistic, Response response) {
        final ReceivedMonthStatisticEvent event = ReceivedMonthStatisticEvent.getEvent(statistic);
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