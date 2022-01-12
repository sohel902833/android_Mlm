package com.sohelsk.boomcash.LocalDb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class NotificationViewDb {
    private Activity activity;
    public NotificationViewDb(Activity activity) {
        this.activity = activity;
    }
    public void setNotificationId(long  notificationId) {
        SharedPreferences sharedPreferences=activity.getSharedPreferences("notificationView", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putLong("notificationId",notificationId);
        editor.commit();
    }


    public long getNotificationId(){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("notificationView", Context.MODE_PRIVATE);

         long notificationId= sharedPreferences.getLong("notificationId",0);
        return  notificationId;
    }
}
