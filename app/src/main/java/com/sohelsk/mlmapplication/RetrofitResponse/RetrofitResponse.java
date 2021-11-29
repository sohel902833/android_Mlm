package com.sohelsk.mlmapplication.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.mlmapplication.DataModel.LoginModel;

public interface RetrofitResponse {
    void onSuccess(String message, ProgressDialog progressDialog);
    void onError(String message, ProgressDialog progressDialog);
}
