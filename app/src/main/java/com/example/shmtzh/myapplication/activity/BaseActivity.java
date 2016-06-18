package com.example.shmtzh.myapplication.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }


}
