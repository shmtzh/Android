package com.example.shmtzh.myapplication.dialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.activity.LoginActivity;

public class RegistrationDialogFragment extends DialogFragment implements View.OnClickListener {
    Button regBtn, okBtn;


    public RegistrationDialogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_registration, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        okBtn = (Button) view.findViewById(R.id.ok);
        regBtn = (Button) view.findViewById(R.id.support);
        okBtn.setOnClickListener(this);
        regBtn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.support:
                String number = "tel:" + LoginActivity.getSupportNumber();
                Uri uri = Uri.parse(number);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(callIntent);
                break;
            case R.id.ok:
                getDialog().dismiss();
                break;
        }
    }


}