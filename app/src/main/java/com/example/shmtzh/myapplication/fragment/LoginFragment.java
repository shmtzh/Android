package com.example.shmtzh.myapplication.fragment;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.dialog.RegistrationDialogFragment;
import com.example.shmtzh.myapplication.event.ReceivedSupportNumberEvent;
import com.squareup.otto.Subscribe;


public class LoginFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView forgotTv;
    Button registrBtn;
    String number;


    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        forgotTv = (TextView) view.findViewById(R.id.forgot_pass);
        forgotTv.setPaintFlags(forgotTv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        registrBtn = (Button) view.findViewById(R.id.regist);
        registrBtn.setOnClickListener(this);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Subscribe
    public void OnReceivedSupportNumber(ReceivedSupportNumberEvent event) {
        number = event.getSupportNumber().getNumber();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist:
                RegistrationDialogFragment dialog = new RegistrationDialogFragment(number);
                dialog.show(getFragmentManager(), "dialog");
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
