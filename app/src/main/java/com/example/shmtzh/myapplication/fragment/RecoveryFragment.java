package com.example.shmtzh.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.activity.LoginActivity;
import com.example.shmtzh.myapplication.event.ReceivedLoginEvent;
import com.example.shmtzh.myapplication.event.ReceivedSupportNumberEvent;
import com.example.shmtzh.myapplication.listener.FragmentInteraction;
import com.squareup.otto.Subscribe;


public class RecoveryFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentInteraction mCommChListener;
    Button callOfficeBtn;


    public RecoveryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onPause() {
        super.onPause();
        getBus().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getBus().register(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recovery, container, false);
        callOfficeBtn = (Button) view.findViewById(R.id.call_office);
        callOfficeBtn.setOnClickListener(this);

        return view;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteraction) {
            mCommChListener = (FragmentInteraction) context;
        } else {
            throw new ClassCastException();
        }
    }


    public void changeFragment(int id) {
        mCommChListener.changeFragment(id);
    }

    @Override
    public void onClick(View v) {
        String number = "tel:" + LoginActivity.getSupportNumber();
        Uri uri = Uri.parse(number);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(callIntent);
    }
}
