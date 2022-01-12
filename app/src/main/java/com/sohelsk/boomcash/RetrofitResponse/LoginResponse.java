package com.sohelsk.boomcash.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.boomcash.DataModel.LoginModel;

public interface LoginResponse {
    void onSuccess(String message, ProgressDialog progressDialog, LoginModel loginModel);
    void onError(String message, ProgressDialog progressDialog);
}
