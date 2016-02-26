package com.example.shmtzh.myapplication.mist;

import android.content.Context;

/**
 * Created by shmtzh on 2/25/16.
 */
public class LocalStorageManager {
    protected static String SharedPreferencesName;
    private static Context mContext;

    protected LocalStorageManager() {

    }

    protected Context getContext() {
        return mContext;
    }

    public static void create(Context context) {
        mContext = context;

    }
}
