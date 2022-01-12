package com.sohelsk.boomcash.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.boomcash.DataModel.TaskListModel;

public interface TaskListResponse {
    void onSuccess(String message, ProgressDialog progressDialog, TaskListModel taskList);
    void onError(String message, ProgressDialog progressDialog);
}
