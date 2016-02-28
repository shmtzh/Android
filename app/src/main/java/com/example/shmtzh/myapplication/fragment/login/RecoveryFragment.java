package com.example.shmtzh.myapplication.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.activity.LoginActivity;
import com.example.shmtzh.myapplication.listener.FragmentInteraction;


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
