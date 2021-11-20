package com.sohelsk.mlmapplication.HomeFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sohelsk.mlmapplication.Adapter.ViewPagerAdapter;
import com.sohelsk.mlmapplication.OrderFragments.OrderFinishedFragment;
import com.sohelsk.mlmapplication.OrderFragments.OrderInvalidFragment;
import com.sohelsk.mlmapplication.OrderFragments.OrderProgressFragment;
import com.sohelsk.mlmapplication.OrderFragments.OrderUnderReviewFragment;
import com.sohelsk.mlmapplication.R;
import com.google.android.material.tabs.TabLayout;


public class OrderFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TextView availableOpportunityTv;
    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_order, container, false);
        init(view);

       adapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager());


        //add Fragment here
        adapter.AddFragment(new OrderProgressFragment(),"In Progress");
        adapter.AddFragment(new OrderUnderReviewFragment(),"Under Review");
        adapter.AddFragment(new OrderFinishedFragment(),"Finished");
        adapter.AddFragment(new OrderInvalidFragment(),"Invalid");
        viewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(viewPager);



        return  view;
    }

    private void init(View view){
        mTabLayout=view.findViewById(R.id.tablayout_id);
        viewPager=view.findViewById(R.id.view_pagerId);
        availableOpportunityTv=view.findViewById(R.id.availableOpportunityTvId);
    }
}