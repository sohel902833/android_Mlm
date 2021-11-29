package com.sohelsk.mlmapplication.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.mlmapplication.DataModel.EarningHistoryListModel;
import com.sohelsk.mlmapplication.DataModel.WithdrawListModel;

public interface WithdrawHistoryResponse {
    void onSuccess(String message, ProgressDialog progressDialog, WithdrawListModel withdrawList);
    void onError(String message, ProgressDialog progressDialog);
}
