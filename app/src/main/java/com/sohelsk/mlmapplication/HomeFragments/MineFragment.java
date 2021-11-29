package com.sohelsk.mlmapplication.HomeFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sohelsk.mlmapplication.ApiCall.UserApi;
import com.sohelsk.mlmapplication.ChatingActivity;
import com.sohelsk.mlmapplication.DataModel.User;
import com.sohelsk.mlmapplication.EarningHisoryActivity;
import com.sohelsk.mlmapplication.InviteFriendsActivity;
import com.sohelsk.mlmapplication.LocalDb.UserDb;
import com.sohelsk.mlmapplication.ProfileActivity;
import com.sohelsk.mlmapplication.ProfileSettingListActivity;
import com.sohelsk.mlmapplication.R;
import com.sohelsk.mlmapplication.RetrofitResponse.UserResponse;
import com.sohelsk.mlmapplication.WithdrawHistoryActivity;


public class MineFragment extends Fragment {


    public MineFragment() {
        // Required empty public constructor
    }

    private TextView emailTv, uidTv, balanceTv, totalRevenueTv, dayIncomeTv, taskRevenueTv, referralIncomeTv, teamWorkIncomeTv;
    private UserDb userDb;
    private ImageView settingImg;
    private UserApi userApi;
    private ProgressDialog progressDialog;
    private TextView currentLevelTv;

    private LinearLayout  inviteFriendsLn, withdrawLn;
    private LinearLayout personalInformationLn, teamReportLn, joinCommunityLn, purchaseCommunityLn, withdrawReportLn, dailyReportLn, incomeBreakDownLn;
    BottomNavigationView bottomNavigationView;

    private FloatingActionButton msgBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        init(view);

        settingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfileSettingListActivity.class));
            }
        });

        withdrawReportLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WithdrawHistoryActivity.class));
            }
        });
        dailyReportLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EarningHisoryActivity.class));
            }
        });   inviteFriendsLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InviteFriendsActivity.class));
            }
        });

        purchaseCommunityLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationView.setSelectedItemId(R.id.member_nav_id);
            }
        });

        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChatingActivity.class));
            }
        });
        personalInformationLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });






        return view;
    }

    private void init(View view) {
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        msgBtn=view.findViewById(R.id.messageIconButton);
        progressDialog = new ProgressDialog(getContext());
        currentLevelTv = view.findViewById(R.id.currentLevelTv);
        userApi = new UserApi(getActivity());
        userDb = new UserDb(getActivity());
        emailTv = view.findViewById(R.id.m_emailTvId);
        uidTv = view.findViewById(R.id.p_uidTvId);
        balanceTv = view.findViewById(R.id.p_balanceTvId);
        totalRevenueTv = view.findViewById(R.id.totalRevenueTvId);
        dayIncomeTv = view.findViewById(R.id.dayIncomeTvId);
        taskRevenueTv = view.findViewById(R.id.taskRevenueTvId);
        referralIncomeTv = view.findViewById(R.id.referralIncomeTvId);
        teamWorkIncomeTv = view.findViewById(R.id.teamWorkIncomeTvId);
        settingImg = view.findViewById(R.id.settingImg);
        inviteFriendsLn = view.findViewById(R.id.inviteFriendsLn);
        withdrawLn = view.findViewById(R.id.withdrawLn);
        personalInformationLn = view.findViewById(R.id.personalInformationLn);
        teamReportLn = view.findViewById(R.id.teamReportLn);
        joinCommunityLn = view.findViewById(R.id.joinCommunityLn);
        purchaseCommunityLn = view.findViewById(R.id.purchaseCommunityLn);
        withdrawReportLn = view.findViewById(R.id.withdrawReportLn);
        dailyReportLn = view.findViewById(R.id.dailyReportLn);
        incomeBreakDownLn = view.findViewById(R.id.incomeBreakDownLn);

        //setup tool bar
        Toolbar toolbar = getActivity().findViewById(R.id.appBarId);
        toolbar.setVisibility(View.GONE);




    }


    @Override
    public void onStart() {
        super.onStart();
        progressDialog.setMessage("Loading..");
        userApi.getCurrentUser(progressDialog, new UserResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, User currentUser) {
                progressDialog.dismiss();
                emailTv.setText("" + currentUser.getPhone());
                uidTv.setText("" + currentUser.getUserId());
                balanceTv.setText("" + currentUser.getBalance());
                totalRevenueTv.setText("" + currentUser.getTotalRevenue());
                dayIncomeTv.setText("" + currentUser.getDayIncome());
                taskRevenueTv.setText("" + currentUser.getTaskRevenue());
                referralIncomeTv.setText("" + currentUser.getReferralIncome());
                teamWorkIncomeTv.setText("" + currentUser.getTeamWorkIncome());

                if (currentUser.getCurrentMembership().getLevelName() != null) {
                    currentLevelTv.setText("Current Level : " + currentUser.getCurrentMembership().getLevelName());
                }


            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
            }
        });


    }
}