package com.sohelsk.boomcash.ApiCall;

import android.app.Activity;
import android.app.ProgressDialog;

import com.sohelsk.boomcash.Api.DataRetrofitApi;
import com.sohelsk.boomcash.DataModel.EarningHistoryListModel;
import com.sohelsk.boomcash.DataModel.GenerationUserListModel;
import com.sohelsk.boomcash.DataModel.IncomeGuideListModel;
import com.sohelsk.boomcash.DataModel.PackageModelList;
import com.sohelsk.boomcash.DataModel.ReferUserListModel;
import com.sohelsk.boomcash.DataModel.VideoTutorialListModel;
import com.sohelsk.boomcash.DataModel.WithdrawListModel;
import com.sohelsk.boomcash.LocalDb.UserDb;
import com.sohelsk.boomcash.RetrofitResponse.GenerationUserResponse;
import com.sohelsk.boomcash.RetrofitResponse.IncomeGuideResponse;
import com.sohelsk.boomcash.RetrofitResponse.IncomeHistoryResponse;
import com.sohelsk.boomcash.RetrofitResponse.PackageRequestResponse;
import com.sohelsk.boomcash.RetrofitResponse.ReferUserResponse;
import com.sohelsk.boomcash.RetrofitResponse.VideoTutorialListResponse;
import com.sohelsk.boomcash.RetrofitResponse.WithdrawHistoryResponse;

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
    public void getReferUserList(ProgressDialog progressDialog, ReferUserResponse retrofitResponses) {
        progressDialog.show();
        Call<ReferUserListModel> call=dataRetrofitApi.getReferUserList(userDb.getToken());
        call.enqueue(new Callback<ReferUserListModel>() {
            @Override
            public void onResponse(Call<ReferUserListModel> call, Response<ReferUserListModel> response) {
                ReferUserListModel incomeGuideList=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess("Success",progressDialog,incomeGuideList);
                        }catch (Exception e){
                            retrofitResponses.onError("No Refer User Found",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("No Refer User Found",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("No Refer User Found",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<ReferUserListModel> call, Throwable t) {
                retrofitResponses.onError("No Refer User Found",progressDialog);

            }
        });
    }
    public void getReferUserByReferCode(ProgressDialog progressDialog,String referCode, ReferUserResponse retrofitResponses) {
        progressDialog.show();
        Call<ReferUserListModel> call=dataRetrofitApi.getReferUserByReferCode(userDb.getToken(),referCode);
        call.enqueue(new Callback<ReferUserListModel>() {
            @Override
            public void onResponse(Call<ReferUserListModel> call, Response<ReferUserListModel> response) {
                ReferUserListModel referUserList=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess("Success",progressDialog,referUserList);
                        }catch (Exception e){
                            retrofitResponses.onError("No Refer User Found",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("No Refer User Found",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("No Refer User Found",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<ReferUserListModel> call, Throwable t) {
                retrofitResponses.onError("No Refer User Found",progressDialog);

            }
        });
    }


    public void getMyGeneration(ProgressDialog progressDialog, GenerationUserResponse retrofitResponses) {
        progressDialog.show();
        Call<GenerationUserListModel> call=dataRetrofitApi.getMyGeneration(userDb.getToken());
        call.enqueue(new Callback<GenerationUserListModel>() {
            @Override
            public void onResponse(Call<GenerationUserListModel> call, Response<GenerationUserListModel> response) {
                GenerationUserListModel referUserList=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess("Success",progressDialog,referUserList);
                        }catch (Exception e){
                            retrofitResponses.onError("No Refer User Found",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("No Refer User Found",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("No Refer User Found",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<GenerationUserListModel> call, Throwable t) {
                retrofitResponses.onError("No Refer User Found",progressDialog);

            }
        });
    }
    public void getTodayEarnHistory(ProgressDialog progressDialog, IncomeHistoryResponse retrofitResponses) {
        progressDialog.show();
        Call<EarningHistoryListModel> call=dataRetrofitApi.getTodayEarningHistory(userDb.getToken());
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
  public void getPackageList(ProgressDialog progressDialog, PackageRequestResponse retrofitResponses) {
        progressDialog.show();
        Call<PackageModelList> call=dataRetrofitApi.getPackagePurchaseList(userDb.getToken());
        call.enqueue(new Callback<PackageModelList>() {
            @Override
            public void onResponse(Call<PackageModelList> call, Response<PackageModelList> response) {
                PackageModelList incomeGuideList=response.body();
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
            public void onFailure(Call<PackageModelList> call, Throwable t) {
                retrofitResponses.onError("Not Found",progressDialog);

            }
        });
    }


}
