package com.example.shmtzh.myapplication.fragment.feed;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.adapter.FeedAdapter;
import com.example.shmtzh.myapplication.event.ReceivedActiveFeedEvent;
import com.example.shmtzh.myapplication.event.ReceivedHistoryFeedEvent;
import com.example.shmtzh.myapplication.event.ReceivedNewFeedEvent;
import com.example.shmtzh.myapplication.fragment.login.BaseFragment;
import com.example.shmtzh.myapplication.model.FeedModel;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;


public class FeedFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button newBtn, activeBtn, historyBtn;
    private RecyclerView mNewRecyclerView, mActiveRecyclerView, mHistoryRecyclerView;

    public FeedFragment() {
        // Required empty public constructor
    }


    public static FeedFragment newInstance(String param1, String param2) {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        newBtn = (Button) view.findViewById(R.id.new_btn);
        activeBtn = (Button) view.findViewById(R.id.active_btn);
        historyBtn = (Button) view.findViewById(R.id.history_btn);
        mNewRecyclerView = (RecyclerView) view.findViewById(R.id.new_recycler_view);
        mActiveRecyclerView = (RecyclerView) view.findViewById(R.id.active_recycler_view);
        mHistoryRecyclerView = (RecyclerView) view.findViewById(R.id.history_recycler_view);

        newBtn.setOnClickListener(this);
        activeBtn.setOnClickListener(this);
        historyBtn.setOnClickListener(this);

        initiateInitialSettings();
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


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        makeAllUnClicked();
        switch (v.getId()) {
            case R.id.new_btn:
                newBtn.setBackground(getResources().getDrawable(R.color.orange));
                newBtn.setTextColor(getResources().getColor(R.color.black));
                mNewRecyclerView.setVisibility(View.VISIBLE);
                mActiveRecyclerView.setVisibility(View.INVISIBLE);
                mHistoryRecyclerView.setVisibility(View.INVISIBLE);
                break;
            case R.id.active_btn:
                activeBtn.setBackground(getResources().getDrawable(R.color.orange));
                activeBtn.setTextColor(getResources().getColor(R.color.black));
                mNewRecyclerView.setVisibility(View.INVISIBLE);
                mActiveRecyclerView.setVisibility(View.VISIBLE);
                mHistoryRecyclerView.setVisibility(View.INVISIBLE);
                break;
            case R.id.history_btn:
                historyBtn.setBackground(getResources().getDrawable(R.color.orange));
                historyBtn.setTextColor(getResources().getColor(R.color.black));
                mNewRecyclerView.setVisibility(View.INVISIBLE);
                mActiveRecyclerView.setVisibility(View.INVISIBLE);
                mHistoryRecyclerView.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Subscribe
    public void onNewFeedEvent(ReceivedNewFeedEvent event) {
        FeedModel list = event.getNewFeed();
        RecyclerView.Adapter mAdapter = new FeedAdapter(list);
        mNewRecyclerView.setAdapter(mAdapter);
    }

    @Subscribe
    public void onActiveFeedEvent(ReceivedActiveFeedEvent event) {
        FeedModel list = event.getActiveFeed();
        RecyclerView.Adapter mAdapter = new FeedAdapter(list);
        mActiveRecyclerView.setAdapter(mAdapter);
    }

    @Subscribe
    public void onHistoryFeedEvent(ReceivedHistoryFeedEvent event) {
        FeedModel list =  event.getHistoryFeed();
        RecyclerView.Adapter mAdapter = new FeedAdapter(list);
        mHistoryRecyclerView.setAdapter(mAdapter);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void makeAllUnClicked() {
        newBtn.setBackground(getResources().getDrawable(R.drawable.borders_black));
        historyBtn.setBackground(getResources().getDrawable(R.drawable.borders_black));
        activeBtn.setBackground(getResources().getDrawable(R.drawable.borders_black));
        newBtn.setTextColor(getResources().getColor(R.color.orange));
        historyBtn.setTextColor(getResources().getColor(R.color.orange));
        activeBtn.setTextColor(getResources().getColor(R.color.orange));
    }

    public void initiateInitialSettings()
    {
        mNewRecyclerView.setHasFixedSize(true);
        mActiveRecyclerView.setHasFixedSize(true);
        mHistoryRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mNewLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager mActiveLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager mHistoryLayoutManager = new LinearLayoutManager(getActivity());
        mNewRecyclerView.setLayoutManager(mNewLayoutManager);
        mActiveRecyclerView.setLayoutManager(mActiveLayoutManager);
        mHistoryRecyclerView.setLayoutManager(mHistoryLayoutManager);
        newBtn.setBackground(getResources().getDrawable(R.color.orange));
        newBtn.setTextColor(getResources().getColor(R.color.black));
        mNewRecyclerView.setVisibility(View.VISIBLE);
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


}
