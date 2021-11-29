package com.sohelsk.mlmapplication.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.mlmapplication.DataModel.MembershipListModel;
import com.sohelsk.mlmapplication.DataModel.VideoTutorialListModel;

public interface VideoTutorialListResponse {
    void onSuccess(String message, ProgressDialog progressDialog, VideoTutorialListModel tutorialList);
    void onError(String message, ProgressDialog progressDialog);
}
