package com.example.transfertandroidproject.http.retrofit.services;

import com.example.transfertandroidproject.http.retrofit.entities.TokenRefresh;
import com.example.transfertandroidproject.http.retrofit.entities.User;
import com.example.transfertandroidproject.http.retrofit.entities.UserLoginInfo;
import com.example.transfertandroidproject.http.retrofit.entities.UserToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("user/login")
    Call<UserToken> getOneUser(@Body UserLoginInfo userLoginInfo);

    @GET("user")
    Call<List<User>> getAllUser();

    @POST("user/refresh")
    Call<UserToken> getRefresh(@Body TokenRefresh tokenRefresh);
}
