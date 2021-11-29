package com.sohelsk.mlmapplication.Api;

import com.sohelsk.mlmapplication.DataModel.EarningHistoryListModel;
import com.sohelsk.mlmapplication.DataModel.IncomeGuideListModel;
import com.sohelsk.mlmapplication.DataModel.TaskListModel;
import com.sohelsk.mlmapplication.DataModel.VideoTutorialListModel;
import com.sohelsk.mlmapplication.DataModel.WithdrawListModel;

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
  @GET("user/withdraw")
    Call<WithdrawListModel> getWithdrawList(@Header("Authorization") String token);

}
