package com.sohelsk.boomcash;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.sohelsk.boomcash.ApiCall.UserApi;
import com.sohelsk.boomcash.DataModel.User;
import com.sohelsk.boomcash.LocalDb.UserDb;
import com.sohelsk.boomcash.RetrofitResponse.RetrofitResponse;

import java.util.HashMap;

public class ProfileSettingListActivity extends AppCompatActivity {

    private CardView withdrawCard,changePassCard,showCodePass;
    private UserDb userDb;
    private UserApi userApi;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting_list);

        init();

        withdrawCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWithdrawAddressDialog();
            }
        });
        showCodePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMemonicDialog();
            }
        });
        changePassCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePasswordDialog();
            }
        });


    }



    private  void init(){
        progressDialog=new ProgressDialog(this);
        userApi=new UserApi(this);
        userDb=new UserDb(this);
        withdrawCard=findViewById(R.id.withdrawAddressCard);
        changePassCard=findViewById(R.id.changePasswordCard);
        showCodePass=findViewById(R.id.fourDigitCodeCard);

        Toolbar toolbar = findViewById(R.id.appBarId);

        ImageView backButton = (ImageView)toolbar.findViewById(R.id.backBtn);
        TextView appBarTv=toolbar.findViewById(R.id.mainTvId);
        TextView tv2=toolbar.findViewById(R.id.layoutTextId);
        tv2.setVisibility(View.GONE);
        appBarTv.setText("Setting");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    public  void showWithdrawAddressDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ProfileSettingListActivity.this);
        View view=getLayoutInflater().inflate(R.layout.withdraw_address_dialog_layout,null);
        builder.setView(view);

        EditText withdrawAddressEt=view.findViewById(R.id.d_withdrawAddressEtId);
        EditText memonicEt=view.findViewById(R.id.d_memonicEtId);
        Button updateButton=view.findViewById(R.id.d_updateButtonId);
        withdrawAddressEt.setText(""+userDb.getUserData().getWithdrawAddress());

        final AlertDialog dialog=builder.create();
        dialog.show();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String withdrawAddress=withdrawAddressEt.getText().toString();
              String memonic=memonicEt.getText().toString();
              if(withdrawAddress.isEmpty()){
                  withdrawAddressEt.setError("Enter Withdraw Address");
                  withdrawAddressEt.requestFocus();
              }else if(memonic.isEmpty()){
                  memonicEt.setError("Enter Memonic Code");
                  memonicEt.requestFocus();
              }else{
                  progressDialog.setMessage("Please Wait..");
                  progressDialog.setTitle("Setting up Withdraw address");
                  HashMap<String,Object> hashMap=new HashMap<>();
                  hashMap.put("secretCode",memonic);
                  hashMap.put("withdrawAddress",withdrawAddress);

                  userApi.setWithdrawAddress(hashMap, progressDialog, new RetrofitResponse() {
                      @Override
                      public void onSuccess(String message, ProgressDialog progressDialog) {
                          progressDialog.dismiss();
                          User user=userDb.getUserData();
                          user.setWithdrawAddress(withdrawAddress);
                          userDb.setUserData(user);
                          Toast.makeText(ProfileSettingListActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                          dialog.dismiss();
                      }

                      @Override
                      public void onError(String message, ProgressDialog progressDialog) {
                        progressDialog.dismiss();
                          Toast.makeText(ProfileSettingListActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                      }
                  });
              }

            }
        });

    }
    public  void changePasswordDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ProfileSettingListActivity.this);
        View view=getLayoutInflater().inflate(R.layout.change_password_dialog_layout,null);
        builder.setView(view);

        EditText newPasswordEt=view.findViewById(R.id.d_newPasswordEtId);
        EditText memonicEt=view.findViewById(R.id.d_memonicEtId);
        Button updateButton=view.findViewById(R.id.d_updateButtonId);

        final AlertDialog dialog=builder.create();
        dialog.show();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String newPassword=newPasswordEt.getText().toString();
              String memonic=memonicEt.getText().toString();
              if(newPassword.isEmpty()){
                  newPasswordEt.setError("Enter New Password");
                  newPasswordEt.requestFocus();
              }else if(memonic.isEmpty()){
                  memonicEt.setError("Enter Memonic Code");
                  memonicEt.requestFocus();
              }else{
                  progressDialog.setMessage("Please Wait..");
                  progressDialog.setTitle("Password Updating..");
                  HashMap<String,Object> hashMap=new HashMap<>();
                  hashMap.put("secretCode",memonic);
                  hashMap.put("newPassword",newPassword);
                  userApi.updatePassword(hashMap, progressDialog, new RetrofitResponse() {
                      @Override
                      public void onSuccess(String message, ProgressDialog progressDialog) {
                          progressDialog.dismiss();
                          Toast.makeText(ProfileSettingListActivity.this, ""+message, Toast.LENGTH_SHORT).show();


                          dialog.dismiss();
                      }

                      @Override
                      public void onError(String message, ProgressDialog progressDialog) {
                        progressDialog.dismiss();
                          Toast.makeText(ProfileSettingListActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                      }
                  });
              }

            }
        });

    }
    public  void showMemonicDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ProfileSettingListActivity.this);
        View view=getLayoutInflater().inflate(R.layout.message_dialouge_layout,null);
        builder.setView(view);

        TextView textView=view.findViewById(R.id.text1);
        Button okButton=view.findViewById(R.id.okButtonId);

        textView.setText("Memonic Code : "+userDb.getUserData().getSecretCode());
        final AlertDialog dialog=builder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyText(""+userDb.getUserData().getSecretCode());
                dialog.dismiss();

            }
        });

    }

    private void copyText(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("text", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(ProfileSettingListActivity.this, "Text Copied to Clipboard.", Toast.LENGTH_SHORT).show();
    }
}