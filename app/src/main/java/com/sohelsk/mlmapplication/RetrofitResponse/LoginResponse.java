package com.sohelsk.mlmapplication.RetrofitResponse;

import android.app.ProgressDialog;
import android.widget.ProgressBar;

import com.google.gson.annotations.SerializedName;
import com.sohelsk.mlmapplication.DataModel.LoginModel;

public interface LoginResponse {
    void onSuccess(String message, ProgressDialog progressDialog, LoginModel loginModel);
    void onError(String message, ProgressDialog progressDialog);
}
