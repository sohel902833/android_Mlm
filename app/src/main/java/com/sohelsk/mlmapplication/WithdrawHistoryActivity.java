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
import com.sohelsk.mlmapplication.Adapter.WithdrawListAdapter;
import com.sohelsk.mlmapplication.ApiCall.DataApi;
import com.sohelsk.mlmapplication.DataModel.IncomeGuideListModel;
import com.sohelsk.mlmapplication.DataModel.WithdrawListModel;
import com.sohelsk.mlmapplication.LocalDb.UserDb;
import com.sohelsk.mlmapplication.RetrofitResponse.IncomeGuideResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.WithdrawHistoryResponse;

public class WithdrawHistoryActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private UserDb userDb;
    private DataApi dataApi;
    private WithdrawListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_history);
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
        appBarTv.setText("Withdraw History");
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
        dataApi.getWithdrawHistory(progressDialog, new WithdrawHistoryResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, WithdrawListModel withdrawList) {
                progressDialog.dismiss();
                adapter=new WithdrawListAdapter(WithdrawHistoryActivity.this,withdrawList.getWithdrawList());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(WithdrawHistoryActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}