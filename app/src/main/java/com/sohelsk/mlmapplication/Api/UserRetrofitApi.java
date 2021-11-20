package com.sohelsk.mlmapplication.Api;



import com.sohelsk.mlmapplication.DataModel.LoginModel;
import com.sohelsk.mlmapplication.RetrofitResponse.LoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface UserRetrofitApi {
//   @GET("admin/cashin/{type}")
//    Call<CashInListModel> getCashIn(@Header("Authorization") String token, @Path("type") String cashInType);

    @POST("user/login")
    Call<LoginModel> loginUser(@Body Map<String,Object> user);
    @POST("user/signup")
    Call<LoginModel> registerUser(@Body Map<String,Object> user);

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
