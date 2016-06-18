package com.example.shmtzh.myapplication.fragment.feed;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shmtzh.myapplication.R;
import com.example.shmtzh.myapplication.adapter.FeedAdapter;
import com.example.shmtzh.myapplication.adapter.InformationAdapter;
import com.example.shmtzh.myapplication.fragment.login.BaseFragment;

import java.util.ArrayList;

public class InformationFragment extends BaseFragment {
    private RecyclerView mInformationView;
    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    public InformationFragment() {
        // Required empty public constructor
    }

    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        mInformationView = (RecyclerView) view.findViewById(R.id.information_view);
        mInformationView.setHasFixedSize(true);
        RecyclerView.LayoutManager mInfoLayoutManager = new LinearLayoutManager(getActivity());
        mInformationView.setLayoutManager(mInfoLayoutManager);

        ArrayList<String> list = new ArrayList<>();
        list.add("Legal Information");
        list.add("Legal Information");
        list.add("Legal Information");
        list.add("Legal Information");
        list.add("Legal Information");
        list.add("Legal Information");
        list.add("Legal Information");
        list.add("Legal Information");
        list.add("Legal Information");
        RecyclerView.Adapter mAdapter = new InformationAdapter(list);
        mInformationView.setAdapter(mAdapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
