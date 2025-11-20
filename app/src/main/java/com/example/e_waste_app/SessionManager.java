package com.example.e_waste_app;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    private static final String PREF_NAME = "user_session";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void saveUser(String email, String password) {
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public void login() {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getEmail() {
        return prefs.getString(KEY_EMAIL, "");
    }

    public String getPassword() {
        return prefs.getString(KEY_PASSWORD, "");
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
}
