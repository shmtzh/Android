package com.example.shmtzh.myapplication;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.shmtzh.myapplication.bus.EventBus;
import com.example.shmtzh.myapplication.bus.OttoBus;
import com.example.shmtzh.myapplication.mist.Constant;
import com.example.shmtzh.myapplication.mist.LocalStorageManager;
import com.example.shmtzh.myapplication.rest.ZClient;

import retrofit.RequestInterceptor;

/**

 * Created by shmtzh on 2/25/16.
 */
public class Z extends Application {
    private ZClient mRest;
    private ZClient mRestNoCache;
    private static EventBus mBus = new OttoBus();


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "creation of app");
        LocalStorageManager.create(this);
        createRestClient();
    }


    public static EventBus getBus() {
        return mBus;
    }

    public ZClient getRestClient() {
        return mRest;
    }


    private void createRestClient() {
        final boolean shouldLog = true;
        mRest = ZClient.getRestWithCache(getBus(), getCacheDir().getAbsolutePath(),
                createInterceptor(), Constant.MAIN_SERVER_ADDR, shouldLog);
        mRestNoCache = ZClient.getRestWithNoCache(getBus(), Constant.MAIN_SERVER_ADDR, shouldLog);
    }

    public RequestInterceptor createInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5, application/json, application/octet-stream");

                    int maxAge = 60; // read from cache for 1 minute
                    request.addHeader("Cache-Control", "max-age=" + maxAge + ", max-stale=" + maxAge);
//                    request.addHeader("Content-Type", "application/json");

                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
//                    request.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
                    request.addHeader("Content-Type", "application/x-www-form-urlencoded");

            }
        };
    }



}
