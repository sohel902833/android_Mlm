package com.sohelsk.mlmapplication.ApiCall;

import android.app.Activity;
import android.app.ProgressDialog;

import com.sohelsk.mlmapplication.Api.DataRetrofitApi;
import com.sohelsk.mlmapplication.Api.UserRetrofitApi;
import com.sohelsk.mlmapplication.DataModel.EarningHistoryListModel;
import com.sohelsk.mlmapplication.DataModel.IncomeGuideListModel;
import com.sohelsk.mlmapplication.DataModel.LoginModel;
import com.sohelsk.mlmapplication.DataModel.MembershipListModel;
import com.sohelsk.mlmapplication.DataModel.TaskListModel;
import com.sohelsk.mlmapplication.DataModel.User;
import com.sohelsk.mlmapplication.DataModel.VideoTutorialListModel;
import com.sohelsk.mlmapplication.DataModel.WithdrawListModel;
import com.sohelsk.mlmapplication.LocalDb.UserDb;
import com.sohelsk.mlmapplication.RetrofitResponse.IncomeGuideResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.IncomeHistoryResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.LoginResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.MembershipListResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.TaskListResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.UserResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.VideoTutorialListResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.WithdrawHistoryResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataApi {

    DataRetrofitApi dataRetrofitApi;
    UserDb userDb;
    private Activity context;
    public DataApi(Activity context) {
        this.context = context;

        userDb = new UserDb(context);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dataRetrofitApi = retrofit.create(DataRetrofitApi.class);

    }
    public void getVideoTutorialList(ProgressDialog progressDialog, VideoTutorialListResponse retrofitResponses) {
        progressDialog.show();
        Call<VideoTutorialListModel> call=dataRetrofitApi.getVideoTutorialList(userDb.getToken());
        call.enqueue(new Callback<VideoTutorialListModel>() {
            @Override
            public void onResponse(Call<VideoTutorialListModel> call, Response<VideoTutorialListModel> response) {
                VideoTutorialListModel membershipList=response.body();
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
            public void onFailure(Call<VideoTutorialListModel> call, Throwable t) {
                retrofitResponses.onError("Not Found",progressDialog);

            }
        });
    }
    public void getInComeGuide(ProgressDialog progressDialog, IncomeGuideResponse retrofitResponses) {
        progressDialog.show();
        Call<IncomeGuideListModel> call=dataRetrofitApi.getIncomeGuide(userDb.getToken());
        call.enqueue(new Callback<IncomeGuideListModel>() {
            @Override
            public void onResponse(Call<IncomeGuideListModel> call, Response<IncomeGuideListModel> response) {
                IncomeGuideListModel incomeGuideList=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess("Success",progressDialog,incomeGuideList);
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
            public void onFailure(Call<IncomeGuideListModel> call, Throwable t) {
                retrofitResponses.onError("Not Found",progressDialog);

            }
        });
    }
    public void getEarningHistory(ProgressDialog progressDialog, IncomeHistoryResponse retrofitResponses) {
        progressDialog.show();
        Call<EarningHistoryListModel> call=dataRetrofitApi.getEarningHistory(userDb.getToken());
        call.enqueue(new Callback<EarningHistoryListModel>() {
            @Override
            public void onResponse(Call<EarningHistoryListModel> call, Response<EarningHistoryListModel> response) {
                EarningHistoryListModel incomeGuideList=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess("Success",progressDialog,incomeGuideList);
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
            public void onFailure(Call<EarningHistoryListModel> call, Throwable t) {
                retrofitResponses.onError("Not Found",progressDialog);

            }
        });
    }
    public void getWithdrawHistory(ProgressDialog progressDialog, WithdrawHistoryResponse retrofitResponses) {
        progressDialog.show();
        Call<WithdrawListModel> call=dataRetrofitApi.getWithdrawList(userDb.getToken());
        call.enqueue(new Callback<WithdrawListModel>() {
            @Override
            public void onResponse(Call<WithdrawListModel> call, Response<WithdrawListModel> response) {
                WithdrawListModel incomeGuideList=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess("Success",progressDialog,incomeGuideList);
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
            public void onFailure(Call<WithdrawListModel> call, Throwable t) {
                retrofitResponses.onError("Not Found",progressDialog);

            }
        });
    }


}
