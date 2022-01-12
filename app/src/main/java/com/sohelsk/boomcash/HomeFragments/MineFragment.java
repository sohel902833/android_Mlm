package com.sohelsk.boomcash.HomeFragments;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sohelsk.boomcash.ApiCall.UserApi;
import com.sohelsk.boomcash.ChatingActivity;
import com.sohelsk.boomcash.DataModel.User;
import com.sohelsk.boomcash.DepositRecordActivity;
import com.sohelsk.boomcash.EarningHisoryActivity;
import com.sohelsk.boomcash.InviteFriendsActivity;
import com.sohelsk.boomcash.LocalDb.NotificationViewDb;
import com.sohelsk.boomcash.LocalDb.SettingDb;
import com.sohelsk.boomcash.LocalDb.UserDb;
import com.sohelsk.boomcash.LoginActivity;
import com.sohelsk.boomcash.NotificationActivity;
import com.sohelsk.boomcash.ProfileActivity;
import com.sohelsk.boomcash.ProfileSettingListActivity;
import com.sohelsk.boomcash.R;
import com.sohelsk.boomcash.RetrofitResponse.UserResponse;
import com.sohelsk.boomcash.TeamReportActivity;
import com.sohelsk.boomcash.TodayEarningHistoryActivity;
import com.sohelsk.boomcash.WithdrawActivity;
import com.sohelsk.boomcash.WithdrawHistoryActivity;


public class MineFragment extends Fragment {


    public MineFragment() {
        // Required empty public constructor
    }

    private TextView emailTv, uidTv, balanceTv, totalRevenueTv, dayIncomeTv, taskRevenueTv, referralIncomeTv, teamWorkIncomeTv;
    private UserDb userDb;
    private ImageView settingImg,notificationImg,uidCopyBtn,redBulbBtn;
    private UserApi userApi;
    private ProgressDialog progressDialog;
    private TextView currentLevelTv;

    private LinearLayout  inviteFriendsLn, withdrawLn;
    private LinearLayout personalInformationLn,logoutLn, teamReportLn, joinCommunityLn, purchaseCommunityLn, withdrawReportLn, dailyReportLn, incomeBreakDownLn;
    BottomNavigationView bottomNavigationView;

    private FloatingActionButton msgBtn;
    private SettingDb settingDb;
    private DatabaseReference newNotificationRef;
    private NotificationViewDb notificationViewDb;
    long notificationId=0;
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
        notificationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NotificationActivity.class);
                intent.putExtra("notificationId",notificationId);
                getContext().startActivity(intent);

            }
        });

        withdrawReportLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WithdrawHistoryActivity.class));
            }
        });withdrawLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WithdrawActivity.class));
            }
        });

        incomeBreakDownLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EarningHisoryActivity.class));
            }
        });

        inviteFriendsLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InviteFriendsActivity.class));
            }
        });

        purchaseCommunityLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DepositRecordActivity.class));

                //bottomNavigationView.setSelectedItemId(R.id.member_nav_id);
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
        uidCopyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyText(""+userDb.getUserData().getUserId());
            }
        });
        logoutLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              logoutUser();
            }
        });

        dailyReportLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TodayEarningHistoryActivity.class));
            }
        });
        teamReportLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TeamReportActivity.class));
            }
        });

        joinCommunityLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Uri uri = Uri.parse(settingDb.getSetting().getHelpCenter());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }catch (Exception err){

                }
            }
        });



        return view;
    }


    private void logoutUser(){
        userDb.removeUserData();
        Intent intent=new Intent(getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();

    }

    private void init(View view) {
        newNotificationRef= FirebaseDatabase.getInstance().getReference().child("NewNotification");
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        notificationViewDb=new NotificationViewDb(getActivity());
        redBulbBtn=view.findViewById(R.id.redBulb);
        msgBtn=view.findViewById(R.id.messageIconButton);
        logoutLn=view.findViewById(R.id.logoutLn);
        uidCopyBtn=view.findViewById(R.id.uidCopyBtnId);
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
        notificationImg = view.findViewById(R.id.notificationImg);
        inviteFriendsLn = view.findViewById(R.id.inviteFriendsLn);
        withdrawLn = view.findViewById(R.id.withdrawLn);
        personalInformationLn = view.findViewById(R.id.personalInformationLn);
        teamReportLn = view.findViewById(R.id.teamReportLn);
        joinCommunityLn = view.findViewById(R.id.joinCommunityLn);
        purchaseCommunityLn = view.findViewById(R.id.purchaseCommunityLn);
        withdrawReportLn = view.findViewById(R.id.withdrawReportLn);
        dailyReportLn = view.findViewById(R.id.dailyReportLn);
        incomeBreakDownLn = view.findViewById(R.id.incomeBreakDownLn);
        settingDb=new SettingDb(getActivity());
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
                uidTv.setText("UID: " + currentUser.getUserId());
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
        newNotificationRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                     notificationId=snapshot.child("notificationId").getValue(Long.class);
                     long dbNotificationId=notificationViewDb.getNotificationId();
                    if(dbNotificationId!=notificationId){
                        redBulbBtn.setVisibility(View.VISIBLE);
                    }else{
                        redBulbBtn.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void copyText(String text) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("referCode", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getContext(), "Text Copied to Clipboard.", Toast.LENGTH_SHORT).show();
    }
}