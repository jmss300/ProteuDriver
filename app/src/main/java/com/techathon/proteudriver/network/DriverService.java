package com.techathon.proteudriver.network;

import com.techathon.proteudriver.models.Driver;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Proteu on 07/01/16.
 */
public interface DriverService {
    @GET("Drivers/{id}")
    Call<Driver> getDriver(@Path("id") int driverId);

    @GET("Drivers/me")
    Call<Driver> getCurrentUser();
}
