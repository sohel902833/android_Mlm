package com.sohelsk.mlmapplication.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.mlmapplication.DataModel.IncomeGuideListModel;
import com.sohelsk.mlmapplication.DataModel.VideoTutorialListModel;

public interface IncomeGuideResponse {
    void onSuccess(String message, ProgressDialog progressDialog, IncomeGuideListModel incomeGuideLis);
    void onError(String message, ProgressDialog progressDialog);
}
