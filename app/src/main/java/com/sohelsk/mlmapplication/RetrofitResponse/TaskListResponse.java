package com.sohelsk.mlmapplication.RetrofitResponse;

import android.app.ProgressDialog;

import com.sohelsk.mlmapplication.DataModel.MembershipListModel;
import com.sohelsk.mlmapplication.DataModel.TaskListModel;

public interface TaskListResponse {
    void onSuccess(String message, ProgressDialog progressDialog, TaskListModel taskList);
    void onError(String message, ProgressDialog progressDialog);
}
