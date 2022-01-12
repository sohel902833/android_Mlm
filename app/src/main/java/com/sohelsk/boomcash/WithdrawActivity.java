package com.sohelsk.boomcash;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sohelsk.boomcash.ApiCall.UserApi;
import com.sohelsk.boomcash.LocalDb.UserDb;
import com.sohelsk.boomcash.RetrofitResponse.RetrofitResponse;

import java.util.HashMap;

public class WithdrawActivity extends AppCompatActivity {

    private EditText amountEt,memonicEt;
    private Button withdrawBtn;
    private UserDb userDb;
    private UserApi userApi;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        init();

        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount=amountEt.getText().toString();
                String secretCode=memonicEt.getText().toString();
                if(amount.isEmpty()){
                    amountEt.setError("Enter Amount..");
                    amountEt.requestFocus();
                }else if(secretCode.isEmpty()){
                    memonicEt.setError("Enter Memonic Number.");
                    memonicEt.requestFocus();
                }else{
                    withdraw(amount,secretCode);
                }
            }
        });





    }
    private  void  init(){
        progressDialog=new ProgressDialog(this);

        userDb=new UserDb(this);
        userApi=new UserApi(this);
        amountEt=findViewById(R.id.w_AmountEt);
        memonicEt=findViewById(R.id.w_memonicEtId);
        withdrawBtn=findViewById(R.id.w_WithdrawButton);

        //setup toolbar
        Toolbar toolbar = findViewById(R.id.appBarId);

        ImageView backButton = (ImageView) toolbar.findViewById(R.id.backBtn);
        TextView appBarTv = toolbar.findViewById(R.id.mainTvId);
        TextView tv2 = toolbar.findViewById(R.id.layoutTextId);
        tv2.setVisibility(View.GONE);
        appBarTv.setText("Withdraw");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void withdraw(String amount,String secretCode){
        progressDialog.setMessage("Sending Withdraw Request.");
        progressDialog.setTitle("Please Wait.");
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("amount",amount);
        hashMap.put("secretCode",secretCode);
        userApi.withdrawBalance(hashMap, progressDialog, new RetrofitResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(WithdrawActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(WithdrawActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}