package com.sohelsk.mlmapplication.ApiCall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sohelsk.mlmapplication.Api.UserRetrofitApi;
import com.sohelsk.mlmapplication.DataModel.LoginModel;
import com.sohelsk.mlmapplication.LocalDb.UserDb;
import com.sohelsk.mlmapplication.RetrofitResponse.LoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApi {

    UserRetrofitApi userRetrofitApi;
    UserDb userDb;
    private Activity context;
    public UserApi(Activity context) {
        this.context = context;

        userDb = new UserDb(context);

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
}
