package com.sohelsk.boomcash.LocalDb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.sohelsk.boomcash.DataModel.JobViewModel;

public class JobViewDb {
    private Activity activity;
    public JobViewDb(Activity activity) {
        this.activity = activity;
    }
    public static  final long DELAYTIME=8000;
    public void setJobView(int  jobId) {
        SharedPreferences sharedPreferences=activity.getSharedPreferences("jobView", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putInt("jobId",jobId);
        editor.putLong("time",System.currentTimeMillis());


        editor.commit();
    }


    public JobViewModel getJobView(){
        JobViewModel jobViewModel=null;
        SharedPreferences sharedPreferences=activity.getSharedPreferences("jobView", Context.MODE_PRIVATE);

         int jobId= sharedPreferences.getInt("jobId",-1);
         long time= sharedPreferences.getLong("time",0);
         jobViewModel=new JobViewModel(jobId,time);

        return  jobViewModel;
    }
    public void clearJobView(){
        SharedPreferences userShared = activity.getSharedPreferences("jobView", Context.MODE_PRIVATE);
        userShared.edit().clear().apply();
    }



}
