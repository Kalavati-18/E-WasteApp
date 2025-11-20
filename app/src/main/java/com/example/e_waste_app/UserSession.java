package com.example.e_waste_app;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public UserSession(Context context) {
        pref = context.getSharedPreferences("userSession", Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setLoggedIn(boolean status) {
        editor.putBoolean("isLoggedIn", status);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean("isLoggedIn", false);
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }

    public void saveUser(String email, String pass) {
        editor.putString("email", email);
        editor.putString("password", pass);
        editor.apply();
    }

    public String getEmail() {
        return pref.getString("email", "");
    }

    public String getPassword() {
        return pref.getString("password", "");
    }
}
