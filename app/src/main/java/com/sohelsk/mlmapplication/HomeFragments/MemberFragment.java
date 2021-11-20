package com.sohelsk.mlmapplication.HomeFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sohelsk.mlmapplication.R;

public class MemberFragment extends Fragment {


    public MemberFragment() {
        // Required empty public constructor
    }

    private TextView currentMembershipTv,numOfJobsTv,monthlyIncomeTv,validityPeriodTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_member, container, false);
        init(view);


        return  view;
    }
    private void init(View view){
       currentMembershipTv=view.findViewById(R.id.m_currentMembershipTvId);
       numOfJobsTv=view.findViewById(R.id.m_c_numOfJobsPerDayTvId);
       monthlyIncomeTv=view.findViewById(R.id.m_c_monthlyIncomeTvId);
       validityPeriodTv=view.findViewById(R.id.validityPeriodDayTvId);

    }
}