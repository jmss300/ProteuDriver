package com.techathon.proteudriver.managers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.techathon.proteudriver.LoginActivity;


/**
 * Created by Proteu on 08/01/16.
 */
public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private static final int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "GuestPerf";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_ACCESS_TOKEN = "AccessToken";

    public SessionManager(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.editor = sharedPreferences.edit();
    }


    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public String getLoggedAccessToken() {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, "");
    }

    public void createLoginSession(String email, String accessToken) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    public void startLoginActivity() {
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
