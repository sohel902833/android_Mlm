package com.sohelsk.boomcash.Api;

import com.sohelsk.boomcash.DataModel.EarningHistoryListModel;
import com.sohelsk.boomcash.DataModel.GenerationUserListModel;
import com.sohelsk.boomcash.DataModel.IncomeGuideListModel;
import com.sohelsk.boomcash.DataModel.PackageModelList;
import com.sohelsk.boomcash.DataModel.ReferUserListModel;
import com.sohelsk.boomcash.DataModel.VideoTutorialListModel;
import com.sohelsk.boomcash.DataModel.WithdrawListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DataRetrofitApi {
    @GET("user/video-tutorial")
    Call<VideoTutorialListModel> getVideoTutorialList(@Header("Authorization") String token);
  @GET("user/income-guide")
    Call<IncomeGuideListModel> getIncomeGuide(@Header("Authorization") String token);
   @GET("user/earn-history")
    Call<EarningHistoryListModel> getEarningHistory(@Header("Authorization") String token);
   @GET("user/today/earn-history")
    Call<EarningHistoryListModel> getTodayEarningHistory(@Header("Authorization") String token);
   @GET("user/generation")
    Call<GenerationUserListModel> getMyGeneration(@Header("Authorization") String token);
  @GET("user/withdraw")
    Call<WithdrawListModel> getWithdrawList(@Header("Authorization") String token);
  @GET("user/package")
    Call<PackageModelList> getPackagePurchaseList(@Header("Authorization") String token);
  @GET("user/refer-user")
    Call<ReferUserListModel> getReferUserList(@Header("Authorization") String token);
  @GET("user/refer/user/{referCode}")
    Call<ReferUserListModel> getReferUserByReferCode(@Header("Authorization") String token,@Path("referCode") String referCode);
}
