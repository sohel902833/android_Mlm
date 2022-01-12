package com.sohelsk.boomcash.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.boomcash.DataModel.GenerationUserListModel;

public interface GenerationUserResponse {
    void onSuccess(String message, ProgressDialog progressDialog, GenerationUserListModel generationListModel);
    void onError(String message, ProgressDialog progressDialog);
}
