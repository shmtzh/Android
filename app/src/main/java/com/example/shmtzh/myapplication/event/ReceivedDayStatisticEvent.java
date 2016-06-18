package com.example.shmtzh.myapplication.event;

import android.util.Log;

import com.example.shmtzh.myapplication.model.RegularStatistic;

/**
 * Created by shmtzh on 3/12/16.
 */
public class ReceivedDayStatisticEvent extends RemoteEvent {
    RegularStatistic statistic;

    public ReceivedDayStatisticEvent(RegularStatistic statistic) {
        this.statistic = statistic;
    }

    public static ReceivedDayStatisticEvent getEvent(RegularStatistic statistic) {
        Log.d("yes", "ReceivedAllStatistic: " + statistic.getSum());

        return new ReceivedDayStatisticEvent(statistic);
    }

    public RegularStatistic getDayStatistic() {
        return statistic;
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
