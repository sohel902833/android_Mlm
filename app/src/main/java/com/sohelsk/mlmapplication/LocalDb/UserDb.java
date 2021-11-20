package com.sohelsk.mlmapplication.LocalDb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.sohelsk.mlmapplication.DataModel.User;

import okhttp3.internal.cache.DiskLruCache;

public class UserDb {
    private Activity activity;
    public UserDb(Activity activity) {
        this.activity = activity;
    }
    public void setUserData(User user) {
        SharedPreferences sharedPreferences=activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

         Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("user", json);

        editor.commit();
    }

    public void setToken(String token){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("token", token);
        editor.commit();
    }




    public User getUserData(){
        User user=null;
        SharedPreferences sharedPreferences=activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("user","");
         user = gson.fromJson(json, User.class);
        return  user;
    }
    public String getToken(){
        String token="";
        SharedPreferences sharedPreferences=activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token","");
        return token;
    }

    public void removeUserData(){
        SharedPreferences userShared = activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);
        userShared.edit().clear().apply();
    }


}
