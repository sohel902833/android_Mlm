package com.sohelsk.boomcash.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.boomcash.DataModel.MembershipListModel;

public interface MembershipListResponse {
    void onSuccess(String message, ProgressDialog progressDialog, MembershipListModel membershipList);
    void onError(String message, ProgressDialog progressDialog);
}
