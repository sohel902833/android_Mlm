package com.sohelsk.mlmapplication.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.mlmapplication.DataModel.EarningHistoryListModel;
import com.sohelsk.mlmapplication.DataModel.IncomeGuideListModel;

public interface IncomeHistoryResponse {
    void onSuccess(String message, ProgressDialog progressDialog, EarningHistoryListModel earningHistoryList);
    void onError(String message, ProgressDialog progressDialog);
}
