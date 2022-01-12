package com.sohelsk.boomcash.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.boomcash.DataModel.PackageModelList;

public interface PackageRequestResponse {
    void onSuccess(String message, ProgressDialog progressDialog, PackageModelList packageModelList);
    void onError(String message, ProgressDialog progressDialog);
}
