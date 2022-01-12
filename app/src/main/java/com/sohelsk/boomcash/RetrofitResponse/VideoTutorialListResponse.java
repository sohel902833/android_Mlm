package com.sohelsk.boomcash.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.boomcash.DataModel.VideoTutorialListModel;

public interface VideoTutorialListResponse {
    void onSuccess(String message, ProgressDialog progressDialog, VideoTutorialListModel tutorialList);
    void onError(String message, ProgressDialog progressDialog);
}
