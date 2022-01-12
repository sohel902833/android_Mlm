package com.sohelsk.boomcash;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.sohelsk.boomcash.RetrofitResponse.RetrofitResponse;

import java.util.HashMap;

public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText phoneEt,memonicEt,passwordEt,confirmPasswordEt;
    private Button forgetPassButton;
    private UserApi userApi;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        init();

        forgetPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=phoneEt.getText().toString();
                String secretCode=memonicEt.getText().toString();
                String password=passwordEt.getText().toString();
                String confirmPassword=confirmPasswordEt.getText().toString();

                if(phone.isEmpty()){
                    phoneEt.setError("Enter Phone Number.");
                    phoneEt.requestFocus();
                }else if(secretCode.isEmpty()){
                    memonicEt.setError("Enter Memonic Code.");
                    memonicEt.requestFocus();
                }else if(password.isEmpty()){
                    passwordEt.setError("Enter New Password");
                    passwordEt.requestFocus();
                }else if(confirmPassword.isEmpty()){
                    confirmPasswordEt.setError("Confirm Password.");
                    confirmPasswordEt.requestFocus();
                }else{
                    if(password.equals(confirmPassword)){

                        progressDialog.setMessage("Resetting Your Password.");
                        progressDialog.setTitle("Please Wait..");
                        HashMap<String,Object> hashMap=new HashMap<>();
                        hashMap.put("phone",phone);
                        hashMap.put("secretCode",secretCode);
                        hashMap.put("newPassword",password.trim());

                        userApi.forgetPassword(hashMap, progressDialog, new RetrofitResponse() {
                            @Override
                            public void onSuccess(String message, ProgressDialog progressDialog) {
                                progressDialog.dismiss();
                                Toast.makeText(ForgetPasswordActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgetPasswordActivity.this,LoginActivity.class));
                                finish();
                            }

                            @Override
                            public void onError(String message, ProgressDialog progressDialog) {
                                progressDialog.dismiss();
                                Toast.makeText(ForgetPasswordActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                            }
                        });


                    }else{
                        confirmPasswordEt.setError("Password Doesn't Matched.");
                        confirmPasswordEt.requestFocus();
                    }
                }


            }
        });


    }
    private  void init(){
        userApi = new UserApi(this);
        progressDialog = new ProgressDialog(this);
        phoneEt=findViewById(R.id.f_emailEditTextId);
        passwordEt=findViewById(R.id.f_newPasswordEdittextId);
        confirmPasswordEt=findViewById(R.id.f_confirmNewPasswordEdittextId);
        memonicEt=findViewById(R.id.f_memonicCodeEt);
        forgetPassButton=findViewById(R.id.f_forgetPasswordButton);

        //setup toolbar
        Toolbar toolbar = findViewById(R.id.appBarId);
        ImageView backButton = (ImageView)toolbar.findViewById(R.id.backBtn);
        TextView appBarTv=toolbar.findViewById(R.id.mainTvId);
        appBarTv.setText("Forget Password");
        TextView tv2=toolbar.findViewById(R.id.layoutTextId);
        tv2.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });





    }
    private  void forgetPassword(){


    }
}