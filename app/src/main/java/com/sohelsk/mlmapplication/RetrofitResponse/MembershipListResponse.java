package com.sohelsk.mlmapplication.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.mlmapplication.DataModel.MembershipListModel;
import com.sohelsk.mlmapplication.DataModel.User;

public interface MembershipListResponse {
    void onSuccess(String message, ProgressDialog progressDialog, MembershipListModel membershipList);
    void onError(String message, ProgressDialog progressDialog);
}
