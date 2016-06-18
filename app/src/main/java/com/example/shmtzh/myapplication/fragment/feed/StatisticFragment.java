package com.example.shmtzh.myapplication.fragment.feed;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.activity.FeedActivity;
import com.example.shmtzh.myapplication.event.ReceivedCompeleteStatisticEvent;
import com.example.shmtzh.myapplication.event.ReceivedDayStatisticEvent;
import com.example.shmtzh.myapplication.event.ReceivedMonthStatisticEvent;
import com.example.shmtzh.myapplication.event.ReceivedWeekStatisticEvent;
import com.example.shmtzh.myapplication.fragment.login.BaseFragment;
import com.example.shmtzh.myapplication.model.CompleteStatistic;
import com.example.shmtzh.myapplication.model.RegularStatistic;
import com.squareup.otto.Subscribe;


public class StatisticFragment extends BaseFragment implements View.OnClickListener {
    TextView rating, allDeliveries, allIncome;
    Button dayBtn, weekBtn, monthBtn;
    TextView income, deliveries;
    private final String TAG = getClass().getSimpleName();


    public StatisticFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);
        rating = (TextView) view.findViewById(R.id.rating);
        allIncome = (TextView) view.findViewById(R.id.all_income);
        allDeliveries = (TextView) view.findViewById(R.id.all_deliveries);
        income = (TextView) view.findViewById(R.id.income);
        deliveries = (TextView) view.findViewById(R.id.deliveries);
        dayBtn = (Button) view.findViewById(R.id.day_btn);
        weekBtn = (Button) view.findViewById(R.id.week_btn);
        monthBtn = (Button) view.findViewById(R.id.month_btn);

        dayBtn.setOnClickListener(this);
        weekBtn.setOnClickListener(this);
        monthBtn.setOnClickListener(this);

        makeInitialSettings();

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
        makeAllUnClicked();
        switch (v.getId()) {
            case R.id.day_btn:
                makeViewSelected(dayBtn);
                changeStatisticUI(FeedActivity.dayStatistic);
                break;
            case R.id.week_btn:
                makeViewSelected(weekBtn);
                changeStatisticUI(FeedActivity.weekStatistic);
                break;
            case R.id.month_btn:
                makeViewSelected(monthBtn);
                changeStatisticUI(FeedActivity.monthStatistic);
                break;

        }
    }

    public void makeInitialSettings() {
        allIncome.setText(String.valueOf(FeedActivity.allStatistic.getSum()));
        rating.setText(String.valueOf(FeedActivity.allStatistic.getRating()));
        allDeliveries.setText(String.valueOf(FeedActivity.allStatistic.getDeliveries()));
        changeStatisticUI(FeedActivity.dayStatistic);
        makeViewSelected(dayBtn);
    }

    public void changeStatisticUI(RegularStatistic model) {
        income.setText(String.valueOf(model.getSum()));
        deliveries.setText(String.valueOf(model.getDeliveries()));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void makeAllUnClicked() {
        dayBtn.setBackground(getResources().getDrawable(R.drawable.borders_black));
        monthBtn.setBackground(getResources().getDrawable(R.drawable.borders_black));
        weekBtn.setBackground(getResources().getDrawable(R.drawable.borders_black));
        dayBtn.setTextColor(getResources().getColor(R.color.orange));
        weekBtn.setTextColor(getResources().getColor(R.color.orange));
        monthBtn.setTextColor(getResources().getColor(R.color.orange));
    }

    public void makeViewSelected(Button button) {
        button.setBackground(getResources().getDrawable(R.color.orange));
        button.setTextColor(getResources().getColor(R.color.black));
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

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }


}
