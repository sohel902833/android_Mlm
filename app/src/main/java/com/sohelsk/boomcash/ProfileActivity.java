package com.sohelsk.boomcash;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sohelsk.boomcash.DataModel.User;
import com.sohelsk.boomcash.LocalDb.UserDb;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTv,emailTv,balanceTv,uidTv,totalRevenueTv,taskRevenueTv,referCodeTv;
    private UserDb userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();

        User user=userDb.getUserData();

        nameTv.setText(""+user.getName());
        emailTv.setText(""+user.getPhone());
        balanceTv.setText("Balance: "+user.getBalance());
        uidTv.setText("UID: "+user.getUserId());
        totalRevenueTv.setText("Total Revenue: "+user.getTotalRevenue());
        taskRevenueTv.setText("Task Revenue: "+user.getTaskRevenue());
        referCodeTv.setText("Refer Code: "+user.getMyReferCode());


    }
    private void init(){
        userDb=new UserDb(this);
        nameTv=findViewById(R.id.p_nameTvId);
        emailTv=findViewById(R.id.p_emailTvId);
        balanceTv=findViewById(R.id.p_balanceTvID);
        uidTv=findViewById(R.id.p_userIdTv);
        totalRevenueTv=findViewById(R.id.p_totalRevenueTvId);
        taskRevenueTv=findViewById(R.id.p_taskRevenueId);
        referCodeTv=findViewById(R.id.p_referCodeTvId);


        //setup toolbar
        Toolbar toolbar = findViewById(R.id.appBarId);
        ImageView backButton = (ImageView)toolbar.findViewById(R.id.backBtn);
        TextView appBarTv=toolbar.findViewById(R.id.mainTvId);
        TextView tv2=toolbar.findViewById(R.id.layoutTextId);
        tv2.setVisibility(View.GONE);
        appBarTv.setText("Profile");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}