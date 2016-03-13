package com.techathon.proteudriver.utils;

import android.util.Patterns;

/**
 * Created by Proteu on 09/01/16.
 */
public class InputValidator {
    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }

    public static boolean isNameValid(String name) {
        return name.length() > 4 && name.length() <= 15;
    }
}
