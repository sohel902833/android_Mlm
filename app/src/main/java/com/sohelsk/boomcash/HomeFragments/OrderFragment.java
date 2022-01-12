package com.sohelsk.boomcash.HomeFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sohelsk.boomcash.Adapter.ViewPagerAdapter;
import com.sohelsk.boomcash.ChatingActivity;
import com.sohelsk.boomcash.DataModel.User;
import com.sohelsk.boomcash.LocalDb.UserDb;
import com.sohelsk.boomcash.OrderFragments.OrderFinishedFragment;
import com.sohelsk.boomcash.OrderFragments.OrderInvalidFragment;
import com.sohelsk.boomcash.OrderFragments.OrderProgressFragment;
import com.sohelsk.boomcash.OrderFragments.OrderUnderReviewFragment;
import com.sohelsk.boomcash.R;
import com.google.android.material.tabs.TabLayout;


public class OrderFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TextView availableOpportunityTv;
    private UserDb userDb;

    private FloatingActionButton msgBtn;
    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_order, container, false);
        init(view);

       adapter=new ViewPagerAdapter(getChildFragmentManager());


        //add Fragment here
        adapter.AddFragment(new OrderProgressFragment(),"In Progress");
        adapter.AddFragment(new OrderUnderReviewFragment(),"Under Review");
        adapter.AddFragment(new OrderFinishedFragment(),"Finished");
        adapter.AddFragment(new OrderInvalidFragment(),"Invalid");
        viewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(viewPager);


        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChatingActivity.class));
            }
        });




        return  view;
    }

    private void init(View view){

        msgBtn=view.findViewById(R.id.messageIconButton);
        userDb=new UserDb(getActivity());
        mTabLayout=view.findViewById(R.id.tablayout_id);
        viewPager=view.findViewById(R.id.view_pagerId);
        availableOpportunityTv=view.findViewById(R.id.availableOpportunityTvId);

        //setup toolbar
        Toolbar toolbar = getActivity().findViewById(R.id.appBarId);
        toolbar.setVisibility(View.VISIBLE);

        TextView appBarTv=toolbar.findViewById(R.id.layoutTextId);
        appBarTv.setVisibility(View.GONE);




        User user=userDb.getUserData();
        if(user.getTodayDone()!=null){
            if(user.getCurrentMembership().getLevelName()!=null){
                int left=user.getCurrentMembership().getNumOfJobs()-user.getTodayDone();
                availableOpportunityTv.setText(""+left);
            }
        }


    }
}