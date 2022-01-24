package com.example.visitapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.example.visitapp.GetPrefId;

import java.util.List;


public interface UserService {

   @Headers("Content-Type: application/json")
   @POST("users/login")
   Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

   @Headers("Content-Type: application/json")
   @POST("users/add")
   Call<SignupResponse> userSignup(@Body SignupRequest signupRequest);

   @Headers("Content-Type: application/json")
   @POST("users/update/{id}")
   Call<UpdateResponse> userUpdate(@Path("id") String _id, @Body UpdateRequest updateRequest);

}

