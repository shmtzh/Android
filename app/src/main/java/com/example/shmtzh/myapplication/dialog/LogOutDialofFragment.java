package com.example.shmtzh.myapplication.dialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.activity.LoginActivity;

/**
 * Created by shmtzh on 3/5/16.
 */
public class LogOutDialofFragment extends DialogFragment implements View.OnClickListener {
    Button cancelBtn, okBtn;


    public LogOutDialofFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_logout, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        okBtn = (Button) view.findViewById(R.id.ok);
        cancelBtn = (Button) view.findViewById(R.id.cancel);
        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                SharedPreferences mPrefs = getActivity().getPreferences(getActivity().MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.remove("token");
                prefsEditor.apply();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.cancel:
                getDialog().dismiss();
                break;
        }
    }



}
