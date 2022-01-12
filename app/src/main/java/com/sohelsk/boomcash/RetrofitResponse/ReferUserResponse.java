package com.sohelsk.boomcash.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.boomcash.DataModel.ReferUserListModel;

public interface ReferUserResponse {
    void onSuccess(String message, ProgressDialog progressDialog, ReferUserListModel referUserList);
    void onError(String message, ProgressDialog progressDialog);
}
