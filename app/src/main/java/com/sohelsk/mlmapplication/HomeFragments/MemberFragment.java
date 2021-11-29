package com.sohelsk.mlmapplication.HomeFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sohelsk.mlmapplication.Adapter.MemberShipListAdapter;
import com.sohelsk.mlmapplication.ApiCall.UserApi;
import com.sohelsk.mlmapplication.ChatingActivity;
import com.sohelsk.mlmapplication.DataModel.CurrentMembership;
import com.sohelsk.mlmapplication.DataModel.MembershipListModel;
import com.sohelsk.mlmapplication.DataModel.User;
import com.sohelsk.mlmapplication.LocalDb.UserDb;
import com.sohelsk.mlmapplication.PurchasePackageActivity;
import com.sohelsk.mlmapplication.R;
import com.sohelsk.mlmapplication.RetrofitResponse.MembershipListResponse;

public class MemberFragment extends Fragment {


    public MemberFragment() {
        // Required empty public constructor
    }

    private TextView currentMembershipTv,numOfJobsTv,monthlyIncomeTv,validityPeriodTv;
    private UserDb userDb;
    private ProgressDialog progressDialog;
    private MemberShipListAdapter memberShipListAdapter;
    private RecyclerView membershipListRecyclerView;
    private UserApi userApi;

    private FloatingActionButton msgBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_member, container, false);
        init(view);

        User currentUser=userDb.getUserData();
        CurrentMembership currentMembership=currentUser.getCurrentMembership();
        if(currentMembership.getLevelName()!=null && currentMembership.getLevelId()!=null && currentMembership.getMonthlyIncome()!=null){

            currentMembershipTv.setText(currentMembership.getLevelName()+" : "+currentMembership.getPrice()+" USD");
            numOfJobsTv.setText("Number Of Jobs Per Day: "+currentMembership.getNumOfJobs());
            monthlyIncomeTv.setText("Monthly Income : "+currentMembership.getMonthlyIncome()+" USD");
            validityPeriodTv.setText("Validity Period Day : "+currentMembership.getValidityDay());
        }else{
            currentMembershipTv.setText("Free : 0 USD");
            numOfJobsTv.setText("Number Of Jobs Per Day: 0");
            monthlyIncomeTv.setText("Monthly Income : 0USD");
            validityPeriodTv.setText("Validity Period Day : ");
        }

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
        userApi=new UserApi(getActivity());
        userDb=new UserDb(getActivity());
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
       currentMembershipTv=view.findViewById(R.id.m_currentMembershipTvId);
       numOfJobsTv=view.findViewById(R.id.m_c_numOfJobsPerDayTvId);
       monthlyIncomeTv=view.findViewById(R.id.m_c_monthlyIncomeTvId);
       validityPeriodTv=view.findViewById(R.id.validityPeriodDayTvId);
       membershipListRecyclerView=view.findViewById(R.id.membershipListRecyclerViewid);
       membershipListRecyclerView.setHasFixedSize(true);
       membershipListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //setup toolbar
        Toolbar toolbar = getActivity().findViewById(R.id.appBarId);
        TextView appBarTv=toolbar.findViewById(R.id.layoutTextId);
        appBarTv.setVisibility(View.GONE);



    }

    @Override
    public void onStart() {
        super.onStart();
        userApi.getAllMembershipLists(progressDialog, new MembershipListResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, MembershipListModel membershipList) {
                progressDialog.dismiss();
                memberShipListAdapter=new MemberShipListAdapter(getContext(),membershipList);
                membershipListRecyclerView.setAdapter(memberShipListAdapter);

                memberShipListAdapter.setOnItemClickListner(new MemberShipListAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClick(int position) {
                        CurrentMembership currentMembership=membershipList.getMembership().get(position);
                        Intent intent=new Intent(getActivity(), PurchasePackageActivity.class);
                        intent.putExtra("usdt",String.valueOf(currentMembership.getPrice()));
                        intent.putExtra("membershpId",String.valueOf(currentMembership.getLevelId()));

                        startActivity(intent);

                    }
                });


            }
            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}