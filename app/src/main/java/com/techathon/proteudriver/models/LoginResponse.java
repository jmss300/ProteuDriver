package com.techathon.proteudriver.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Proteu on 07/01/16.
 */
public class LoginResponse {
    @SerializedName("id")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
