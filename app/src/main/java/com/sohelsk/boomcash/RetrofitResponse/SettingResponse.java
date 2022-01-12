package com.sohelsk.boomcash.RetrofitResponse;

import com.sohelsk.boomcash.DataModel.SettingModel;

public interface SettingResponse {
    void onSuccess(String message, SettingModel settingModel);
    void onError(String message);
}
