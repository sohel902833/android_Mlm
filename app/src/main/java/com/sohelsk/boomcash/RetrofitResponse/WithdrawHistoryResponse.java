package com.sohelsk.boomcash.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.boomcash.DataModel.WithdrawListModel;

public interface WithdrawHistoryResponse {
    void onSuccess(String message, ProgressDialog progressDialog, WithdrawListModel withdrawList);
    void onError(String message, ProgressDialog progressDialog);
}
