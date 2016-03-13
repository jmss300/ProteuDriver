package com.techathon.proteudriver.app;

import android.app.Application;

import com.techathon.proteudriver.models.Driver;
import com.techathon.proteudriver.network.DriverService;
import com.techathon.proteudriver.network.LoginService;
import com.techathon.proteudriver.network.ServiceGenerator;


/**
 * Created by Proteu on 07/01/16.
 */
public class App extends Application {
    private static LoginService loginService;
    private static DriverService clubService;
    public static Driver currentUser;

    @Override
    public void onCreate() {
        super.onCreate();

        loginService = ServiceGenerator.createService(LoginService.class);
    }

    public static LoginService getLoginService() {
        return loginService;
    }

    public static void setClubService(String accessToken) {
        clubService = ServiceGenerator.createService(DriverService.class, accessToken);
    }

    public static DriverService getDriverService() {
        return clubService;
    }

    public static void setCurrentUser(Driver driver) {
        currentUser = driver;
    }
}
