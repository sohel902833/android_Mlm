package com.sohelsk.boomcash;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sohelsk.boomcash.Adapter.NotificationAdapter;
import com.sohelsk.boomcash.DataModel.NotificationModel;
import com.sohelsk.boomcash.LocalDb.NotificationViewDb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private List<NotificationModel> notificationList=new ArrayList<>();

    private NotificationAdapter notificationAdapter;
    private DatabaseReference databaseReference;
    private NotificationViewDb notificationViewDb;
    long notificationId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
         notificationId=getIntent().getLongExtra("notificationId",0);
        init();

    }
    private  void init(){
        notificationViewDb=new NotificationViewDb(this);
        recyclerView=findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog=new ProgressDialog(this);

        databaseReference= FirebaseDatabase.getInstance().getReference("Notification");
        //setup toolbar
        Toolbar toolbar = findViewById(R.id.appBarId);
        ImageView backButton = (ImageView)toolbar.findViewById(R.id.backBtn);
        TextView appBarTv=toolbar.findViewById(R.id.mainTvId);
        appBarTv.setText("Notifications.");
        TextView tv2=toolbar.findViewById(R.id.layoutTextId);
        tv2.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        progressDialog.setMessage("Loading..");
        progressDialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    notificationViewDb.setNotificationId(notificationId);
                    notificationList.clear();
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        NotificationModel notificationModel=snapshot1.getValue(NotificationModel.class);
                        notificationList.add(notificationModel);
                    }
                    Collections.reverse(notificationList);
                    progressDialog.dismiss();
                    notificationAdapter=new NotificationAdapter(NotificationActivity.this,notificationList);
                    recyclerView.setAdapter(notificationAdapter);
                }else{
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });



    }
}