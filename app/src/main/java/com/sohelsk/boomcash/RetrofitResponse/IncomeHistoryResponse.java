package com.sohelsk.boomcash.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.boomcash.DataModel.EarningHistoryListModel;

public interface IncomeHistoryResponse {
    void onSuccess(String message, ProgressDialog progressDialog, EarningHistoryListModel earningHistoryList);
    void onError(String message, ProgressDialog progressDialog);
}
