package com.sohelsk.mlmapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sohelsk.mlmapplication.ApiCall.UserApi;
import com.sohelsk.mlmapplication.DataModel.LoginModel;
import com.sohelsk.mlmapplication.DataModel.User;
import com.sohelsk.mlmapplication.RetrofitResponse.LoginResponse;

import java.util.HashMap;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    private EditText fullNameEt, phoneEt, fourDigitCodeEt, passwordEt, confirmPasswordEt, referCodeEt;
    private Button registerButton;
    private TextView loginLinkTv;
    private UserApi userApi;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        loginLinkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void init() {
        userApi = new UserApi(this);
        progressDialog = new ProgressDialog(this);
        fullNameEt = findViewById(R.id.r_fullNameEditTextId);
        phoneEt = findViewById(R.id.r_phoneEditTextId);
        fourDigitCodeEt = findViewById(R.id.r_4digitCodeEtId);
        passwordEt = findViewById(R.id.r_passwordEdittextId);
        confirmPasswordEt = findViewById(R.id.r_confirmPasswordEdittextId);
        referCodeEt = findViewById(R.id.r_InviteCodeEdittext);
        registerButton = findViewById(R.id.r_RegisterButton);
        loginLinkTv = findViewById(R.id.r_loginLink);

    }

    private void registerUser() {
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Creating Your Account.");
        progressDialog.setCancelable(false);

        String name = fullNameEt.getText().toString();
        String phone = phoneEt.getText().toString();
        String secretCode = fourDigitCodeEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        String confirmPassword = confirmPasswordEt.getText().toString();
        String referCode = referCodeEt.getText().toString();
        if (name.isEmpty()) {
            fullNameEt.setError("Please Enter Your Name");
            fullNameEt.requestFocus();
        } else if (phone.isEmpty()) {
            phoneEt.setError("Please Enter Your Phone");
            phoneEt.requestFocus();
        } else if (secretCode.isEmpty()) {
            fourDigitCodeEt.setError("Please Enter Your Secret Code");
            fourDigitCodeEt.requestFocus();
        } else if (password.isEmpty()) {
            passwordEt.setError("Please Enter Your Password");
            passwordEt.requestFocus();
        } else if (confirmPassword.isEmpty()) {
            confirmPasswordEt.setError("Retype Your Password");
            confirmPasswordEt.requestFocus();
        } else if (password.length() < 6) {
            passwordEt.setError("Password Length Minimum 6 Charaters Long");
            passwordEt.requestFocus();
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordEt.setError("Password Doesn\'t Matched");
            confirmPasswordEt.requestFocus();
        }else{
            HashMap<String,Object> userMap=new HashMap<>();
            userMap.put("name",name);
            userMap.put("phone",phone);
            userMap.put("password",password);
            userMap.put("secretCode",secretCode);
            userMap.put("referCode",referCode);

            userApi.registerUser(userMap, progressDialog, new LoginResponse() {
                @Override
                public void onSuccess(String message, ProgressDialog progressDialog, LoginModel loginModel) {
                    Toast.makeText(RegisterActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                    sendUserToMainActivity();
                }

                @Override
                public void onError(String message, ProgressDialog progressDialog) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


    private void sendUserToMainActivity(){
        Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}