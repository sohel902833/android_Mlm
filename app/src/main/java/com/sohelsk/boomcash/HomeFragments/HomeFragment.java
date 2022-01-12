package com.sohelsk.boomcash.HomeFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sohelsk.boomcash.ChatingActivity;
import com.sohelsk.boomcash.IncomeGuideActivity;
import com.sohelsk.boomcash.InviteFriendsActivity;
import com.sohelsk.boomcash.LocalDb.SettingDb;
import com.sohelsk.boomcash.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sohelsk.boomcash.YoutubeTutorialActivity;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    String[] demoTextList = {
            "Congratulations***1478 upgrade VIP1",
            "Congratulations***7898 upgrade  VIP2",
            "Congratulations***1278 upgrade  VIP3",
            "Congratulations***8888 upgrade  VIP2",
            "Congratulations***4578 upgrade  VIP3",
            "Congratulations***7898 upgrade  VIP1",
            "Congratulations***1278 upgrade  VIP2",
            "Congratulations***328 upgrade  VIP3",
            "Congratulations***4578 upgrade  VIP2",
            "Congratulations***2578 upgrade  VIP1",
    };
    BottomNavigationView bottomNavigationView;
    private CardView helpCenterCard, inviteFriendsCard, incomeGuideCard, videoTutorialCard;
    private TextView demoTv;
    private FloatingActionButton msgBtn;
    private SettingDb settingDb;
    long COUNTDOWN = 40000;
    int counter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);


        new CountDownTimer(COUNTDOWN, 8000) {
            public void onTick(long millisUntilFinished) {
                COUNTDOWN = 40000;
                if (demoTextList.length > counter) {
                    demoTv.setText(demoTextList[counter]);
                    counter++;
                } else {
                    counter = 0;
                    demoTv.setText(demoTextList[counter]);
                }


            }

            public void onFinish() {

            }
        }.start();
        videoTutorialCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), YoutubeTutorialActivity.class));
            }
        });


        helpCenterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Uri uri = Uri.parse(settingDb.getSetting().getHelpCenter());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (Exception err) {

                }

            }
        });


        inviteFriendsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InviteFriendsActivity.class));
            }
        });

        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChatingActivity.class));
            }
        });

        incomeGuideCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), IncomeGuideActivity.class));
            }
        });


        return view;
    }

    private void init(View view) {
        settingDb = new SettingDb(getActivity());
        helpCenterCard = view.findViewById(R.id.helpCenterCard);
        inviteFriendsCard = view.findViewById(R.id.inviteFriendsCard);
        incomeGuideCard = view.findViewById(R.id.incomeGuideCard);
        videoTutorialCard = view.findViewById(R.id.videoTutorialCard);
        msgBtn = view.findViewById(R.id.messageIconButton);
        demoTv = view.findViewById(R.id.demoTvID);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        Toolbar toolbar = getActivity().findViewById(R.id.appBarId);
        toolbar.setVisibility(View.VISIBLE);


    }
}