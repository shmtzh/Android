package com.example.shmtzh.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.event.ReceivedSupportNumberEvent;
import com.example.shmtzh.myapplication.fragment.login.LoginFragment;
import com.example.shmtzh.myapplication.fragment.login.RecoveryFragment;
import com.example.shmtzh.myapplication.listener.FragmentInteraction;
import com.example.shmtzh.myapplication.rest.ZClient;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;

public class LoginActivity extends BaseActivity implements FragmentInteraction {
    private  final String TAG = getClass().getSimpleName();
    static String supportNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ZClient client = getRestClient();
        client.getTheNumber();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new LoginFragment())
                .commit();

    }

    public static String getSupportNumber() {
        return supportNumber;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
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
        supportNumber = event.getSupportNumber().getNumber();
        setDefaults("support", supportNumber, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToNextFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container, fragment).addToBackStack("").commit();
    }


    @Override
    public void changeFragment(int id) {
        Fragment fragment = null;

        switch (id) {
            case 0:
                fragment = new LoginFragment();
                goToNextFragment(fragment);
                break;
            case 1:
                fragment = new RecoveryFragment();
                goToNextFragment(fragment);
                break;
            case 2:
                Intent intent = new Intent(this, FeedActivity.class);
                ZClient client = getRestClient();
                String token = getDefaults("token", this);
                Log.d(TAG, "onCreate for feed: " + token);
                client.getFeedNew(token);
                client.getFeedActive(token);
                client.getFeedHistory(token);
                startActivity(intent);
                break;
        }

    }
}
