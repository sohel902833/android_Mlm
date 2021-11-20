package com.sohelsk.mlmapplication.HomeFragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sohelsk.mlmapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    BottomNavigationView bottomNavigationView;
    private CardView helpCenterCard,inviteFriendsCard,incomeGuideCard,newsCard,videoTutorialCard;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
            init(view);


            helpCenterCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bottomNavigationView.setSelectedItemId(R.id.mine_nav_id);
//                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new MineFragment()).commit();
//

                }
            });


        return  view;
    }
    private void init(View view){
        helpCenterCard=view.findViewById(R.id.helpCenterCard);
        inviteFriendsCard=view.findViewById(R.id.inviteFriendsCard);
        incomeGuideCard=view.findViewById(R.id.incomeGuideCard);
        newsCard=view.findViewById(R.id.newsCard);
        videoTutorialCard=view.findViewById(R.id.videoTutorialCard);
        bottomNavigationView=getActivity().findViewById(R.id.bottom_navigation);
    }
}