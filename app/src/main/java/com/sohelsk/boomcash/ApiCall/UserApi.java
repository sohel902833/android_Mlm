package com.sohelsk.boomcash.ApiCall;

import android.app.Activity;
import android.app.ProgressDialog;

import com.sohelsk.boomcash.Api.UserRetrofitApi;
import com.sohelsk.boomcash.DataModel.LoginModel;
import com.sohelsk.boomcash.DataModel.MembershipListModel;
import com.sohelsk.boomcash.DataModel.RetrofitModel;
import com.sohelsk.boomcash.DataModel.SettingModel;
import com.sohelsk.boomcash.DataModel.TaskListModel;
import com.sohelsk.boomcash.DataModel.User;
import com.sohelsk.boomcash.LocalDb.SettingDb;
import com.sohelsk.boomcash.LocalDb.UserDb;
import com.sohelsk.boomcash.RetrofitResponse.LoginResponse;
import com.sohelsk.boomcash.RetrofitResponse.MembershipListResponse;
import com.sohelsk.boomcash.RetrofitResponse.RetrofitResponse;
import com.sohelsk.boomcash.RetrofitResponse.SettingResponse;
import com.sohelsk.boomcash.RetrofitResponse.TaskListResponse;
import com.sohelsk.boomcash.RetrofitResponse.UserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApi {

    UserRetrofitApi userRetrofitApi;
    UserDb userDb;
    SettingDb settingDb;
    private Activity context;
    public UserApi(Activity context) {
        this.context = context;

        userDb = new UserDb(context);
settingDb=new SettingDb(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userRetrofitApi = retrofit.create(UserRetrofitApi.class);

    }

    public void loginUser(Map<String, Object> adminMap, ProgressDialog progressDialog, LoginResponse retrofitResponses) {
        progressDialog.show();
        Call<LoginModel> call=userRetrofitApi.loginUser(adminMap);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                LoginModel customResponse=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            userDb.setToken(customResponse.getToken());
                            String message=customResponse==null?"Login Successful.":customResponse.getMessage();
                            retrofitResponses.onSuccess(message,progressDialog,customResponse);
                        }catch (Exception e){
                            String message=customResponse==null?"Login Failed.":customResponse.getMessage();
                            retrofitResponses.onError(message,progressDialog);
                        }
                    }else{
                        String message=customResponse==null?"Login Failed.":customResponse.getMessage();
                        retrofitResponses.onError(message,progressDialog);
                    }
                }else{
                    String message=customResponse==null?"Login Failed.":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                retrofitResponses.onError("Login Failed",progressDialog);

            }
        });
    }
     public void forgetPassword(Map<String, Object> forgetMap, ProgressDialog progressDialog, RetrofitResponse retrofitResponses) {
        progressDialog.show();
        Call<RetrofitModel> call=userRetrofitApi.forgetPassword(forgetMap);
        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                RetrofitModel customResponse=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            String message=customResponse==null?"Password Reset Successful.":customResponse.getMessage();
                            retrofitResponses.onSuccess(message,progressDialog);
                        }catch (Exception e){
                            String message=customResponse==null?"Password Reset Failed.":customResponse.getMessage();
                            retrofitResponses.onError(message,progressDialog);
                        }
                    }else{
                        String message=customResponse==null?"Password Reset Failed.":customResponse.getMessage();
                        retrofitResponses.onError(message,progressDialog);
                    }
                }else{
                    String message=customResponse==null?"Password Reset Failed.":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }
            }
            @Override
            public void onFailure(Call<RetrofitModel> call, Throwable t) {
                retrofitResponses.onError("Login Failed",progressDialog);

            }
        });
    }
    public void registerUser(Map<String, Object> userMap, ProgressDialog progressDialog, LoginResponse retrofitResponses) {
        progressDialog.show();
        Call<LoginModel> call=userRetrofitApi.registerUser(userMap);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                LoginModel customResponse=response.body();

                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            userDb.setToken(customResponse.getToken());
                            String message=customResponse==null?"Register Successful.":customResponse.getMessage();
                            retrofitResponses.onSuccess(message,progressDialog,customResponse);
                        }catch (Exception e){
                            String message=customResponse==null?"Register Failed.":customResponse.getMessage();
                            retrofitResponses.onError(message,progressDialog);
                        }
                    }else{
                        String message=customResponse==null?"Register Failed.":customResponse.getMessage();
                        retrofitResponses.onError(message,progressDialog);
                    }
                }else{
                    String message=customResponse==null?"Register Failed.":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                retrofitResponses.onError("Register Failed",progressDialog);

            }
        });
    }

    public void getCurrentUser(ProgressDialog progressDialog, UserResponse retrofitResponses) {
        progressDialog.show();
        Call<User> call=userRetrofitApi.getCurrentUser(userDb.getToken());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User currentUser=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            userDb.setUserData(currentUser);
                            retrofitResponses.onSuccess("Success",progressDialog,currentUser);
                        }catch (Exception e){
                            retrofitResponses.onError("User Not Found",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("User Not Found",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("User Not Found",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                retrofitResponses.onError("User Not Found",progressDialog);

            }
        });
    }
    public void setWithdrawAddress(Map<String, Object> withdrawAddressMap, ProgressDialog progressDialog, RetrofitResponse retrofitResponses) {
       progressDialog.show();
        Call<RetrofitModel> call=userRetrofitApi.setWithdrawAddress(userDb.getToken(),withdrawAddressMap);
        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                 RetrofitModel customResponse=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            String message=customResponse==null?"Withdraw Address Setup Successful":customResponse.getMessage();
                            retrofitResponses.onSuccess(message,progressDialog);
                        }catch (Exception e){
                            String message=customResponse==null?"Withdraw Address Setup Failed.":customResponse.getMessage();
                            retrofitResponses.onError(message,progressDialog);
                        }
                    }else{
                        String message=customResponse==null?"Withdraw Address Setup Failed.":customResponse.getMessage();
                        retrofitResponses.onError(message,progressDialog);
                    }
                }else{
                    String message=customResponse==null?"Withdraw Address Setup Failed.":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }
            }
            @Override
            public void onFailure(Call<RetrofitModel> call, Throwable t) {
                retrofitResponses.onError("Withdraw Address Setup Failed",progressDialog);

            }
        });
    }
   public void withdrawBalance(Map<String, Object> withdrawMap, ProgressDialog progressDialog, RetrofitResponse retrofitResponses) {
       progressDialog.show();
        Call<RetrofitModel> call=userRetrofitApi.withdrawBalance(userDb.getToken(),withdrawMap);
        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                 RetrofitModel customResponse=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            String message=customResponse==null?"Withdraw Address Setup Successful":customResponse.getMessage();
                            retrofitResponses.onSuccess(message,progressDialog);
                        }catch (Exception e){
                            String message=customResponse==null?"Withdraw Request Send Failed.":customResponse.getMessage();
                            retrofitResponses.onError(message,progressDialog);
                        }
                    }else{
                        String message=customResponse==null?"Withdraw Request Send Failed.":customResponse.getMessage();
                        retrofitResponses.onError(message,progressDialog);
                    }
                }else{
                    String message=customResponse==null?"Withdraw Request Send Failed.":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }
            }
            @Override
            public void onFailure(Call<RetrofitModel> call, Throwable t) {
                retrofitResponses.onError("Withdraw Request Send Failed.",progressDialog);

            }
        });
    }
    public void purchasePackage(Map<String, Object> purchaseMap, ProgressDialog progressDialog, RetrofitResponse retrofitResponses) {
       progressDialog.show();
        Call<RetrofitModel> call=userRetrofitApi.purchasePackage(userDb.getToken(),purchaseMap);
        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                 RetrofitModel customResponse=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            String message=customResponse==null?"Request Sent Successful":customResponse.getMessage();
                            retrofitResponses.onSuccess(message,progressDialog);
                        }catch (Exception e){
                            String message=customResponse==null?"Request Sent Failed.":customResponse.getMessage();
                            retrofitResponses.onError(message,progressDialog);
                        }
                    }else{
                        String message=customResponse==null?"Request Sent Failed.":customResponse.getMessage();
                        retrofitResponses.onError(message,progressDialog);
                    }
                }else{
                    String message=customResponse==null?"Request Sent Failed.":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }
            }
            @Override
            public void onFailure(Call<RetrofitModel> call, Throwable t) {
                retrofitResponses.onError("Request Sent Failed",progressDialog);

            }
        });
    }
    public void updatePassword(Map<String, Object> passwordMap, ProgressDialog progressDialog, RetrofitResponse retrofitResponses) {
       progressDialog.show();
        Call<RetrofitModel> call=userRetrofitApi.updatePassword(userDb.getToken(),passwordMap);
        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                 RetrofitModel customResponse=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            String message=customResponse==null?"Password Update Successful":customResponse.getMessage();
                            retrofitResponses.onSuccess(message,progressDialog);
                        }catch (Exception e){
                            String message=customResponse==null?"Password Update Failed.":customResponse.getMessage();
                            retrofitResponses.onError(message,progressDialog);
                        }
                    }else{
                        String message=customResponse==null?"Password Update Failed.":customResponse.getMessage();
                        retrofitResponses.onError(message,progressDialog);
                    }
                }else{
                    String message=customResponse==null?"Password Update Failed.":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }
            }
            @Override
            public void onFailure(Call<RetrofitModel> call, Throwable t) {
                retrofitResponses.onError("Request Sent Failed",progressDialog);

            }
        });
    }
   public void getAllMembershipLists(ProgressDialog progressDialog, MembershipListResponse retrofitResponses) {
        progressDialog.show();
        Call<MembershipListModel> call=userRetrofitApi.getAllMembershipList(userDb.getToken());
        call.enqueue(new Callback<MembershipListModel>() {
            @Override
            public void onResponse(Call<MembershipListModel> call, Response<MembershipListModel> response) {
                MembershipListModel membershipList=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess("Success",progressDialog,membershipList);
                        }catch (Exception e){
                            retrofitResponses.onError("Not Found",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("Not Found",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("Not Found",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<MembershipListModel> call, Throwable t) {
                retrofitResponses.onError("Not Found",progressDialog);

            }
        });
    }
    public void getMyTask(ProgressDialog progressDialog,String taskType, TaskListResponse retrofitResponses) {
        progressDialog.show();
        Call<TaskListModel> call=userRetrofitApi.getMyTaskList(userDb.getToken(),taskType);
        call.enqueue(new Callback<TaskListModel>() {
            @Override
            public void onResponse(Call<TaskListModel> call, Response<TaskListModel> response) {
                TaskListModel taskList=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess("Success",progressDialog,taskList);
                        }catch (Exception e){
                            retrofitResponses.onError("Not Found",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("Not Found",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("Not Found",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<TaskListModel> call, Throwable t) {
                retrofitResponses.onError("Not Found",progressDialog);

            }
        });
    }
    public void getReceiveTaskList(ProgressDialog progressDialog, TaskListResponse retrofitResponses) {
        progressDialog.show();
        Call<TaskListModel> call=userRetrofitApi.getReceiveTaskList(userDb.getToken());
        call.enqueue(new Callback<TaskListModel>() {
            @Override
            public void onResponse(Call<TaskListModel> call, Response<TaskListModel> response) {
                TaskListModel taskList=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess("Success",progressDialog,taskList);
                        }catch (Exception e){
                            retrofitResponses.onError("Not Found",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("Not Found",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("Not Found",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<TaskListModel> call, Throwable t) {
                retrofitResponses.onError("Not Found",progressDialog);

            }
        });
    }
    public void getCompleteTaskList(ProgressDialog progressDialog, TaskListResponse retrofitResponses) {
        progressDialog.show();
        Call<TaskListModel> call=userRetrofitApi.getCompleteTaskList(userDb.getToken());
        call.enqueue(new Callback<TaskListModel>() {
            @Override
            public void onResponse(Call<TaskListModel> call, Response<TaskListModel> response) {
                TaskListModel taskList=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess("Success",progressDialog,taskList);
                        }catch (Exception e){
                            retrofitResponses.onError("Not Found",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("Not Found",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("Not Found",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<TaskListModel> call, Throwable t) {
                retrofitResponses.onError("Not Found",progressDialog);

            }
        });
    }
    public void getAppSetting( SettingResponse retrofitResponses) {
        Call<SettingModel> call=userRetrofitApi.getApplicationSetting(userDb.getToken());
        call.enqueue(new Callback<SettingModel>() {
            @Override
            public void onResponse(Call<SettingModel> call, Response<SettingModel> response) {
                SettingModel setting=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            settingDb.setSetting(setting);
                            retrofitResponses.onSuccess("Success",setting);
                        }catch (Exception e){
                            retrofitResponses.onError("Not Found");
                        }
                    }else{
                        retrofitResponses.onError("Not Found");
                    }
                }else{
                    retrofitResponses.onError("Not Found");
                }
            }
            @Override
            public void onFailure(Call<SettingModel> call, Throwable t) {
                retrofitResponses.onError("Not Found");

            }
        });
    }
    public void receiveTask(ProgressDialog progressDialog,String taskId, RetrofitResponse retrofitResponses) {
        progressDialog.show();
        Call<RetrofitModel> call=userRetrofitApi.receiveTask(userDb.getToken(),taskId);
        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                RetrofitModel msg=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess(""+msg.getMessage(),progressDialog);
                        }catch (Exception e){
                            retrofitResponses.onError("Receive Failde",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("Receive Failde",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("Receive Failde",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<RetrofitModel> call, Throwable t) {
                retrofitResponses.onError("Receive Failde",progressDialog);

            }
        });
    }
   public void completeTask(ProgressDialog progressDialog,String taskId, RetrofitResponse retrofitResponses) {
        progressDialog.show();
        Call<RetrofitModel> call=userRetrofitApi.completeTask(userDb.getToken(),taskId);
        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                RetrofitModel msg=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess(""+msg.getMessage(),progressDialog);
                        }catch (Exception e){
                            retrofitResponses.onError("Complete Failde",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("Complete Failde",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("Complete Failde",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<RetrofitModel> call, Throwable t) {
                retrofitResponses.onError("Receive Failde",progressDialog);

            }
        });
    }


}
