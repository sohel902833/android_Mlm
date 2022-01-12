package com.sohelsk.boomcash;

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

import com.sohelsk.boomcash.Adapter.PurchasePackageAdapter;
import com.sohelsk.boomcash.ApiCall.DataApi;
import com.sohelsk.boomcash.DataModel.PackageModelList;
import com.sohelsk.boomcash.RetrofitResponse.PackageRequestResponse;

public class DepositRecordActivity extends AppCompatActivity {
        private RecyclerView recyclerView;
        private DataApi dataApi;
        private PurchasePackageAdapter adapter;
        private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_record);
        init();
    }
    private  void init(){
        recyclerView=findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog=new ProgressDialog(this);
        dataApi=new DataApi(this);

        //setup toolbar
        Toolbar toolbar = findViewById(R.id.appBarId);
        ImageView backButton = (ImageView)toolbar.findViewById(R.id.backBtn);
        TextView appBarTv=toolbar.findViewById(R.id.mainTvId);
        appBarTv.setText("Deoposit Record.");
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
        dataApi.getPackageList(progressDialog, new PackageRequestResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, PackageModelList packageModelList) {
                progressDialog.dismiss();
               adapter=new PurchasePackageAdapter(DepositRecordActivity.this,packageModelList.getPackageList());
               recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(DepositRecordActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}