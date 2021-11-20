package com.sohelsk.mlmapplication.HomeFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sohelsk.mlmapplication.Adapter.MemberShipAdapter;
import com.sohelsk.mlmapplication.R;


public class TaskFragment extends Fragment {


    public TaskFragment() {
        // Required empty public constructor
    }

    String[] membershipList={
            "VIP1",
            "VIP2",
            "VIP3",
            "VIP4",
            "VIP5",
            "VIP6",
            "VIP7",
            "VIP8",
            "VIP9",
            "VIP10",
            "VIP11",
            "VIP12",
    };
    MemberShipAdapter memberShipAdapter;

    private RecyclerView recyclerView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view= inflater.inflate(R.layout.fragment_task, container, false);
            init(view);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            memberShipAdapter = new MemberShipAdapter(getContext(),membershipList);
            recyclerView.setAdapter(memberShipAdapter);

            return  view;
        }


        private  void init(View view){
            recyclerView=view.findViewById(R.id.membershipListRecyclerViewid);

        }


}