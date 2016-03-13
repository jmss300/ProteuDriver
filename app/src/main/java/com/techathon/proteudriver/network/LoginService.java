package com.techathon.proteudriver.network;

import com.techathon.proteudriver.models.Driver;
import com.techathon.proteudriver.models.LoginRequest;
import com.techathon.proteudriver.models.LoginResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Proteu on 07/01/16.
 */
public interface LoginService {
    @POST("Drivers/login")
    Call<LoginResponse> login(@Body LoginRequest credentials);

    @POST("Drivers/")
    Call<Driver> createDriver(@Body Driver guest);
}
