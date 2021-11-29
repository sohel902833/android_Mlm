package com.sohelsk.mlmapplication.HomeFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sohelsk.mlmapplication.ChatingActivity;
import com.sohelsk.mlmapplication.IncomeGuideActivity;
import com.sohelsk.mlmapplication.InviteFriendsActivity;
import com.sohelsk.mlmapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sohelsk.mlmapplication.YoutubeTutorialActivity;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    BottomNavigationView bottomNavigationView;
    private CardView helpCenterCard,inviteFriendsCard,incomeGuideCard,videoTutorialCard;

    private FloatingActionButton msgBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
            init(view);



            videoTutorialCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), YoutubeTutorialActivity.class));
                }
            });


            helpCenterCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //bottomNavigationView.setSelectedItemId(R.id.mine_nav_id);
//                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new MineFragment()).commit();
//

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


        return  view;
    }
    private void init(View view){
        helpCenterCard=view.findViewById(R.id.helpCenterCard);
        inviteFriendsCard=view.findViewById(R.id.inviteFriendsCard);
        incomeGuideCard=view.findViewById(R.id.incomeGuideCard);
        videoTutorialCard=view.findViewById(R.id.videoTutorialCard);
        msgBtn=view.findViewById(R.id.messageIconButton);
        bottomNavigationView=getActivity().findViewById(R.id.bottom_navigation);
    }
}