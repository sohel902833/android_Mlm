package com.sohelsk.boomcash.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.boomcash.DataModel.IncomeGuideListModel;

public interface IncomeGuideResponse {
    void onSuccess(String message, ProgressDialog progressDialog, IncomeGuideListModel incomeGuideLis);
    void onError(String message, ProgressDialog progressDialog);
}
