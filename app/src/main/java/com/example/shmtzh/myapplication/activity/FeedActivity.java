package com.example.shmtzh.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.dialog.LogOutDialofFragment;
import com.example.shmtzh.myapplication.event.ReceivedCompeleteStatisticEvent;
import com.example.shmtzh.myapplication.event.ReceivedDayStatisticEvent;
import com.example.shmtzh.myapplication.event.ReceivedMonthStatisticEvent;
import com.example.shmtzh.myapplication.event.ReceivedWeekStatisticEvent;
import com.example.shmtzh.myapplication.fragment.feed.FeedFragment;
import com.example.shmtzh.myapplication.fragment.feed.InformationFragment;
import com.example.shmtzh.myapplication.fragment.feed.ProfileFragment;
import com.example.shmtzh.myapplication.fragment.feed.StatisticFragment;
import com.example.shmtzh.myapplication.fragment.login.LoginFragment;
import com.example.shmtzh.myapplication.model.CompleteStatistic;
import com.example.shmtzh.myapplication.model.RegularStatistic;
import com.example.shmtzh.myapplication.rest.ZClient;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.squareup.otto.Subscribe;

public class FeedActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();
    String supportNumber = "";
    SlidingMenu menu;
  public static  RegularStatistic dayStatistic, weekStatistic, monthStatistic;
 public static  CompleteStatistic allStatistic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        supportNumber = getDefaults("support", this);
        String token = getDefaults("token", this);

        Log.d(TAG, "onCreate for stat: " + token);
        Log.d(TAG, "onCreate: " + supportNumber);
        ZClient client = getRestClient();
        client.getStatistic(token, "day");
        client.getStatistic(token, "week");
        client.getStatistic(token, "month");
        client.getCompleteStatistic(token);

        configureSlidingMenu();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FeedFragment())
                .commit();
    }


    @Subscribe
    public void ReceivedDayStatistic(ReceivedDayStatisticEvent event) {
        dayStatistic = event.getDayStatistic();
    }

    @Subscribe
    public void ReceivedWeekStatistic(ReceivedWeekStatisticEvent event) {
        weekStatistic = event.getWeekStatistic();
    }

    @Subscribe
    public void ReceivedMonthStatistic(ReceivedMonthStatisticEvent event) {
        monthStatistic = event.getMonthStatistic();
    }

    @Subscribe
    public void ReceivedAllStatistic(ReceivedCompeleteStatisticEvent event) {
        allStatistic = event.getCompleteStatistic();
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


    public void configureSlidingMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.sliding_menu);
    }

    public void goToPickUpList(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FeedFragment())
                .commit();
        closeMenu();
    }

    public void goToProfile(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ProfileFragment())
                .commit();
        closeMenu();
    }

    public void goToInformation(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new InformationFragment())
                .commit();
        closeMenu();
    }

    public void goToStatistic(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new StatisticFragment())
                .commit();
        closeMenu();
    }

    public void goToLogOut(View view) {
        LogOutDialofFragment fragment = new LogOutDialofFragment();
        fragment.show(getSupportFragmentManager(), "");
        closeMenu();
    }

    public void callSupport(View view) {
        call();
    }

    public void closeMenu() {
        menu.toggle(true);
    }

    public void call() {
        String number = "tel:" + supportNumber;
        Uri uri = Uri.parse(number);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(callIntent);
    }

}
