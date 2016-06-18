package com.example.shmtzh.myapplication.event;

import com.example.shmtzh.myapplication.model.CompleteStatistic;

/**
 * Created by shmtzh on 3/12/16.
 */
public class ReceivedCompeleteStatisticEvent extends RemoteEvent {
    CompleteStatistic statistic;

    public ReceivedCompeleteStatisticEvent(CompleteStatistic statistic) {
        this.statistic = statistic;
    }

    public static ReceivedCompeleteStatisticEvent getEvent(CompleteStatistic statistic) {
        return new ReceivedCompeleteStatisticEvent(statistic);
    }

    public CompleteStatistic getCompleteStatistic() {
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
