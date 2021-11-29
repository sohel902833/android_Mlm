package com.sohelsk.mlmapplication.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.mlmapplication.DataModel.MembershipListModel;
import com.sohelsk.mlmapplication.DataModel.SettingModel;

public interface SettingResponse {
    void onSuccess(String message, SettingModel settingModel);
    void onError(String message);
}
