package com.sohelsk.mlmapplication.OrderFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sohelsk.mlmapplication.R;


public class OrderUnderReviewFragment extends Fragment {

    public OrderUnderReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_under_review, container, false);
    }
}