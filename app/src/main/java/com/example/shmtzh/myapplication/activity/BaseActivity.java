package com.example.shmtzh.myapplication.activity;

import android.support.v4.app.FragmentActivity;

import com.example.shmtzh.myapplication.Z;
import com.example.shmtzh.myapplication.rest.ZClient;
import com.example.shmtzh.myapplication.bus.EventBus;

/**
 * Created by shmtzh on 2/25/16.
 */
public class BaseActivity extends FragmentActivity {

    public Z getApp() {
        return (Z) (getApplication());
    }

    public ZClient getRestClient() {
        return getApp().getRestClient();
    }

    public EventBus getBus() {
        return getApp().getBus();
    }

}
