package com.example.shmtzh.myapplication.fragment.login;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.dialog.RegistrationDialogFragment;
import com.example.shmtzh.myapplication.event.ReceivedLoginEvent;
import com.example.shmtzh.myapplication.listener.FragmentInteraction;
import com.example.shmtzh.myapplication.model.LoginCredentials;
import com.example.shmtzh.myapplication.rest.ZClient;
import com.squareup.otto.Subscribe;


public class LoginFragment extends BaseFragment implements View.OnClickListener {
    TextView forgotTv;
    Button registrBtn, loginBtn;
    String number;
    FragmentInteraction mCommChListener;
    LoginCredentials login = new LoginCredentials();
    EditText pass, telephone;

    public LoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        forgotTv = (TextView) view.findViewById(R.id.forgot_pass);
        forgotTv.setPaintFlags(forgotTv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        forgotTv.setOnClickListener(this);
        registrBtn = (Button) view.findViewById(R.id.regist);
        registrBtn.setOnClickListener(this);
        loginBtn = (Button) view.findViewById(R.id.login);
        loginBtn.setOnClickListener(this);
        telephone = (EditText) view.findViewById(R.id.teleph_edit);
        pass = (EditText) view.findViewById(R.id.pass_edit);

        return view;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist:
                RegistrationDialogFragment dialog = new RegistrationDialogFragment();
                dialog.show(getFragmentManager(), "dialog");
                break;
            case R.id.forgot_pass:
                changeFragment(1);
                break;
            case R.id.login:
                collectDataFromEditText();
                ZClient client = getRestClient();
                client.login(login);
        }
    }

    @Subscribe
    public void OnReceivedLoginEvent(ReceivedLoginEvent event)
    {
        changeFragment(2);
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

    private void collectDataFromEditText() {
        login.setPhone(telephone.getText().toString());
        login.setPassword(pass.getText().toString());
        login.setDeviceId("662aa2f9 6fc85d5d 0261b8ff 031c8d28 058871d7 f0eab02a e8a35c57 66091013");

    }


    public void changeFragment(int id) {
        mCommChListener.changeFragment(id);
    }

}
