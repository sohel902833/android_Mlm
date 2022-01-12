package com.sohelsk.boomcash.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskListModel {

    @SerializedName("myTasks")
    @Expose
    private List<TaskModel> myTasks = null;

    public List<TaskModel> getMyTasks() {
        return myTasks;
    }

    public void setMyTasks(List<TaskModel> myTasks) {
        this.myTasks = myTasks;
    }

}