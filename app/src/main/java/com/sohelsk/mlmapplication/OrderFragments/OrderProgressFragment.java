package com.sohelsk.mlmapplication.OrderFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sohelsk.mlmapplication.R;


public class OrderProgressFragment extends Fragment {



    public OrderProgressFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_progress, container, false);
    }
}