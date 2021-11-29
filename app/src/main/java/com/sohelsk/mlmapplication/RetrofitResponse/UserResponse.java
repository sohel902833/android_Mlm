package com.sohelsk.mlmapplication.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.mlmapplication.DataModel.LoginModel;
import com.sohelsk.mlmapplication.DataModel.User;

public interface UserResponse {
    void onSuccess(String message, ProgressDialog progressDialog, User user);
    void onError(String message, ProgressDialog progressDialog);
}
