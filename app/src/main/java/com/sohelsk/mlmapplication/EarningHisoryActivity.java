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

import com.sohelsk.mlmapplication.Adapter.EarningHistoryAdapter;
import com.sohelsk.mlmapplication.Adapter.IncomeGuideAdapter;
import com.sohelsk.mlmapplication.ApiCall.DataApi;
import com.sohelsk.mlmapplication.DataModel.EarningHistoryListModel;
import com.sohelsk.mlmapplication.DataModel.IncomeGuideListModel;
import com.sohelsk.mlmapplication.LocalDb.UserDb;
import com.sohelsk.mlmapplication.RetrofitResponse.IncomeGuideResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.IncomeHistoryResponse;

public class EarningHisoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private UserDb userDb;
    private DataApi dataApi;
    private EarningHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning_hisory);
        init();

    }

    private void init() {

        progressDialog = new ProgressDialog(this);
        userDb = new UserDb(this);
        dataApi = new DataApi(this);

        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.appBarId);

        ImageView backButton = (ImageView) toolbar.findViewById(R.id.backBtn);
        TextView appBarTv = toolbar.findViewById(R.id.mainTvId);
        TextView tv2 = toolbar.findViewById(R.id.layoutTextId);
        tv2.setVisibility(View.GONE);
        appBarTv.setText("Earn History");
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

        dataApi.getEarningHistory(progressDialog, new IncomeHistoryResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, EarningHistoryListModel earningHistoryList) {
                progressDialog.dismiss();
                adapter = new EarningHistoryAdapter(EarningHisoryActivity.this, earningHistoryList.getIncomeHistory());
                recyclerView.setAdapter(adapter);

            }
            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(EarningHisoryActivity.this, "" + message, Toast.LENGTH_SHORT).show();

            }
        });
    }
}