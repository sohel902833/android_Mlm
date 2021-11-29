package com.sohelsk.mlmapplication;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sohelsk.mlmapplication.ApiCall.UserApi;
import com.sohelsk.mlmapplication.DataModel.User;
import com.sohelsk.mlmapplication.LocalDb.SettingDb;
import com.sohelsk.mlmapplication.LocalDb.UserDb;
import com.sohelsk.mlmapplication.RetrofitResponse.RetrofitResponse;

import java.util.HashMap;

public class PurchasePackageActivity extends AppCompatActivity {

    private SettingDb settingDb;
    private EditText trxCodeEt;
    private TextView rechargeAddressTv,orderNumberTv,usdtTvId;
    private Button rechargeCopyBtn,usdtCopyBtn,submitBtn;
    private UserApi userApi;

    String orderNumber="",usdtAmount="",membershpId="";
        private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_package);
        orderNumber=""+System.currentTimeMillis();
        usdtAmount=getIntent().getStringExtra("usdt");
        membershpId=getIntent().getStringExtra("membershpId");
        init();

        orderNumberTv.setText(""+orderNumber);
        rechargeAddressTv.setText(""+settingDb.getSetting().getRechargeAddress());
        usdtTvId.setText(""+usdtAmount);

        rechargeCopyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyText(""+settingDb.getSetting().getRechargeAddress());
            }
        });
        usdtCopyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyText(usdtAmount);
            }
        });


    submitBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String trxId=trxCodeEt.getText().toString();
            if(trxId.isEmpty()){
                trxCodeEt.setError("Enter Last 4 Digit Of Trx Number");
                trxCodeEt.requestFocus();
            }else{
                progressDialog.setMessage("Please Wait..");
                progressDialog.setTitle("Sending Request..");
                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("membershipId",membershpId);
                hashMap.put("orderNumber",orderNumber);
                hashMap.put("trxNumber",trxId);

                userApi.purchasePackage(hashMap,progressDialog, new RetrofitResponse() {
                    @Override
                    public void onSuccess(String message, ProgressDialog progressDialog) {
                        progressDialog.dismiss();
                        Toast.makeText(PurchasePackageActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(String message, ProgressDialog progressDialog) {
                        progressDialog.dismiss();
                        Toast.makeText(PurchasePackageActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    });








    }

    private  void init(){
        progressDialog=new ProgressDialog(this);
        userApi=new UserApi(this);
        settingDb=new SettingDb(this);
        trxCodeEt=findViewById(R.id.trxCodeEtId);
        rechargeAddressTv=findViewById(R.id.rechargeAddressTvId);
        orderNumberTv=findViewById(R.id.orderNumberTvId);
        usdtTvId=findViewById(R.id.usdtTvId);
        rechargeCopyBtn=findViewById(R.id.rechargeAddressCopyBtn);
        usdtCopyBtn=findViewById(R.id.usdtCopyBtnId);
        submitBtn=findViewById(R.id.submitBtnId);

        //setup toolbar
        Toolbar toolbar = findViewById(R.id.appBarId);
        ImageView backButton = (ImageView)toolbar.findViewById(R.id.backBtn);
        TextView appBarTv=toolbar.findViewById(R.id.mainTvId);
        TextView tv2=toolbar.findViewById(R.id.layoutTextId);
        tv2.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void copyText(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("referCode", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(PurchasePackageActivity.this, "Text Copied to Clipboard.", Toast.LENGTH_SHORT).show();
    }
}