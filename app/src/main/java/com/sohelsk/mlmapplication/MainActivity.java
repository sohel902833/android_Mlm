package com.sohelsk.mlmapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.sohelsk.mlmapplication.ApiCall.UserApi;
import com.sohelsk.mlmapplication.DataModel.SettingModel;
import com.sohelsk.mlmapplication.DataModel.User;
import com.sohelsk.mlmapplication.HomeFragments.HomeFragment;
import com.sohelsk.mlmapplication.HomeFragments.MemberFragment;
import com.sohelsk.mlmapplication.HomeFragments.MineFragment;
import com.sohelsk.mlmapplication.HomeFragments.OrderFragment;
import com.sohelsk.mlmapplication.HomeFragments.TaskFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sohelsk.mlmapplication.LocalDb.UserDb;
import com.sohelsk.mlmapplication.RetrofitResponse.SettingResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.UserResponse;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private UserDb userDb;
    private ProgressDialog progressDialog;
    private UserApi userApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new HomeFragment()).commit();


    }
    private  void init(){
        userApi=new UserApi(this);
        progressDialog=new ProgressDialog(this);
        userDb=new UserDb(this);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String token=userDb.getToken();
        if(token.isEmpty()){
            sendUserToLoginActivity();
        }else{
            progressDialog.setMessage("Loading..");
            progressDialog.setCancelable(false);
            userApi.getCurrentUser(progressDialog, new UserResponse() {
                @Override
                public void onSuccess(String message, ProgressDialog progressDialog, User user) {
                    progressDialog.dismiss();
                }

                @Override
                public void onError(String message, ProgressDialog progressDialog) {
                    progressDialog.dismiss();
                    userDb.removeUserData();
                    sendUserToLoginActivity();
                }
            });
            userApi.getAppSetting(new SettingResponse() {
                @Override
                public void onSuccess(String message, SettingModel settingModel) {

                }

                @Override
                public void onError(String message) {

                }
            });


        }
    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navListner=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment=null;
                    switch (item.getItemId()){
                        case R.id.home_nav_id:{
                            selectedFragment=new HomeFragment();
                            break;
                        }
                        case R.id.member_nav_id:{
                            selectedFragment=new MemberFragment();
                            break;
                        }
                        case R.id.task_nav_id:{
                            selectedFragment=new TaskFragment();
                            break;
                        }
                        case R.id.order_nav_id:{
                            selectedFragment=new OrderFragment();
                            break;
                        }
                        case R.id.mine_nav_id:{
                            selectedFragment=new MineFragment();
                            break;
                        }
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                            selectedFragment).commit();
                    return  true;
                }
            };


    private void sendUserToLoginActivity(){
        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}