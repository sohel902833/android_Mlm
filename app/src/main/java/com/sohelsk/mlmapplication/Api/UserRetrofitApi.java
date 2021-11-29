package com.sohelsk.mlmapplication.Api;



import com.sohelsk.mlmapplication.DataModel.LoginModel;
import com.sohelsk.mlmapplication.DataModel.MembershipListModel;
import com.sohelsk.mlmapplication.DataModel.RetrofitModel;
import com.sohelsk.mlmapplication.DataModel.SettingModel;
import com.sohelsk.mlmapplication.DataModel.TaskListModel;
import com.sohelsk.mlmapplication.DataModel.User;
import com.sohelsk.mlmapplication.RetrofitResponse.LoginResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.RetrofitResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface UserRetrofitApi {
   @GET("user/")
    Call<User> getCurrentUser(@Header("Authorization") String token);
    @POST("user/login")
    Call<LoginModel> loginUser(@Body Map<String,Object> user);
    @POST("user/signup")
    Call<LoginModel> registerUser(@Body Map<String,Object> user);
    @POST("user/withdraw-address")
    Call<RetrofitModel> setWithdrawAddress(@Header("Authorization") String token,@Body Map<String,Object> withdrawAddress);
    @PUT("user/package")
    Call<RetrofitModel> purchasePackage(@Header("Authorization") String token, @Body Map<String,Object> purchase);
    @PUT("user/password")
    Call<RetrofitModel> updatePassword(@Header("Authorization") String token, @Body Map<String,Object> passwordMap);
    @GET("user/membership-list")
    Call<MembershipListModel> getAllMembershipList(@Header("Authorization") String token);
    @GET("user/my-task/{taskType}")
    Call<TaskListModel> getMyTaskList(@Header("Authorization") String token,@Path("taskType") String taskType);
   @POST("user/task/receive/{taskId}")
   Call<RetrofitModel> receiveTask(@Header("Authorization") String token, @Path("taskId") String taskId);
     @POST("user/task-done/{taskId}")
   Call<RetrofitModel> completeTask(@Header("Authorization") String token, @Path("taskId") String taskId);
    @GET("user/task/receive")
    Call<TaskListModel> getReceiveTaskList(@Header("Authorization") String token);
   @GET("user/setting")
    Call<SettingModel> getApplicationSetting(@Header("Authorization") String token);


//    @POST("admin/cashin/{id}")
//    Call<CustomResponse> paidCashIn(@Header("Authorization") String token,@Path("id") String id);
//
//    @GET("setting")
//    Call<AppSettingModel> getAppSetting(@Header("Authorization") String token);
//    @GET("admin/account")
//    Call<AdminAccountModel> getAdminAccount(@Header("Authorization") String token);
//
//     @GET("admin/account/history")
//     Call<AccountHistoryModelList> getAdminAccountHistory(@Header("Authorization") String token);
//    @POST("admin/account/add")
//    Call<CustomResponse> setAdminAccountBalance(@Header("Authorization") String token,@Body Map<String,Object> accountMap);
//
//
//    @POST("setting")
//    Call<CustomResponse> setAppSetting(@Header("Authorization") String token,@Body Map<String,Object> settingMap);

}
