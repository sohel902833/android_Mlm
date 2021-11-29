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
import com.sohelsk.mlmapplication.RetrofitResponse.LoginResponse;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private EditText phoneEt,passwordEt;
    private TextView forgetPassTv,registerLinkTv;
    private Button loginButton;
    private UserApi userApi;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();






        registerLinkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }
    private  void init(){
        userApi = new UserApi(this);
        progressDialog = new ProgressDialog(this);
        phoneEt=findViewById(R.id.l_emailEditTextId);
        passwordEt=findViewById(R.id.l_passwordEdittextId);
        forgetPassTv=findViewById(R.id.l_forgetPasswordTvId);
        registerLinkTv=findViewById(R.id.l_registerLinkTvId);
        loginButton=findViewById(R.id.l_LoginButton);

    }

    private void loginUser() {
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Loging In.");
        progressDialog.setCancelable(false);

        String phone = phoneEt.getText().toString();
        String password = passwordEt.getText().toString().trim();
       if (phone.isEmpty()) {
            phoneEt.setError("Please Enter Your Phone");
            phoneEt.requestFocus();
        } else if (password.isEmpty()) {
            passwordEt.setError("Please Enter Your Password");
            passwordEt.requestFocus();
        } else{
            HashMap<String,Object> userMap=new HashMap<>();
            userMap.put("phone",phone);
            userMap.put("password",password);

            userApi.loginUser(userMap, progressDialog, new LoginResponse() {
                @Override
                public void onSuccess(String message, ProgressDialog progressDialog, LoginModel loginModel) {
                    Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                    sendUserToMainActivity();
                }

                @Override
                public void onError(String message, ProgressDialog progressDialog) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


    private void sendUserToMainActivity(){
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}