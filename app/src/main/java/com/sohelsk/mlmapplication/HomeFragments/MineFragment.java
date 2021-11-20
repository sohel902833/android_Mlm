package com.sohelsk.mlmapplication.HomeFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sohelsk.mlmapplication.R;


public class MineFragment extends Fragment {


    public MineFragment() {
        // Required empty public constructor
    }
    private TextView emailTv,uidTv,balanceTv,totalRevenueTv,dayIncomeTv,taskRevenueTv,referralIncomeTv,teamWorkIncomeTv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mine, container, false);
        init(view);



        return  view;
    }
    private  void  init(View view){
        emailTv=view.findViewById(R.id.m_emailTvId);
        uidTv=view.findViewById(R.id.p_uidTvId);
        balanceTv=view.findViewById(R.id.p_balanceTvId);
        totalRevenueTv=view.findViewById(R.id.totalRevenueTvId);
        dayIncomeTv=view.findViewById(R.id.dayIncomeTvId);
        taskRevenueTv=view.findViewById(R.id.taskRevenueTvId);
        referralIncomeTv=view.findViewById(R.id.referralIncomeTvId);
        teamWorkIncomeTv=view.findViewById(R.id.teamWorkIncomeTvId);

    }


}