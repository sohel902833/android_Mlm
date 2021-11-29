package com.sohelsk.mlmapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.mlmapplication.Adapter.IncomeGuideAdapter;
import com.sohelsk.mlmapplication.ApiCall.DataApi;
import com.sohelsk.mlmapplication.DataModel.IncomeGuideListModel;
import com.sohelsk.mlmapplication.DataModel.User;
import com.sohelsk.mlmapplication.LocalDb.UserDb;
import com.sohelsk.mlmapplication.RetrofitResponse.IncomeGuideResponse;

public class IncomeGuideActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private UserDb userDb;
    private DataApi dataApi;
    private IncomeGuideAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_guide);
        init();
    }

    private void init() {

        progressDialog=new ProgressDialog(this);
        userDb=new UserDb(this);
        dataApi=new DataApi(this);

        recyclerView = findViewById(R.id.incomeGuideRecyclerViewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.appBarId);

        ImageView backButton = (ImageView) toolbar.findViewById(R.id.backBtn);
        TextView appBarTv = toolbar.findViewById(R.id.mainTvId);
        TextView tv2 = toolbar.findViewById(R.id.layoutTextId);
        tv2.setVisibility(View.GONE);
        appBarTv.setText("Income Guides.");
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
        dataApi.getInComeGuide(progressDialog, new IncomeGuideResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, IncomeGuideListModel incomeGuideLis) {
                progressDialog.dismiss();
                adapter=new IncomeGuideAdapter(IncomeGuideActivity.this,incomeGuideLis.getIncomeGuide());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(IncomeGuideActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}