package com.sohelsk.boomcash.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.boomcash.DataModel.User;

public interface UserResponse {
    void onSuccess(String message, ProgressDialog progressDialog, User user);
    void onError(String message, ProgressDialog progressDialog);
}
