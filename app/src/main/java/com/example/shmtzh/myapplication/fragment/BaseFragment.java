package com.example.shmtzh.myapplication.fragment;

import android.support.v4.app.Fragment;

import com.example.shmtzh.myapplication.Z;
import com.example.shmtzh.myapplication.bus.EventBus;
import com.example.shmtzh.myapplication.rest.ZClient;

/**
 * Created by shmtzh on 2/25/16.
 */
public class BaseFragment extends Fragment {
    public Z getApp() {
        return (Z) (getActivity().getApplication());
    }

    public ZClient getRestClient() {
        return getApp().getRestClient();
    }

    public EventBus getBus() {
        return getApp().getBus();
    }
}
