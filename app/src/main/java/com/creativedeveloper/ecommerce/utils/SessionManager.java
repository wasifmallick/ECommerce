package com.creativedeveloper.ecommerce.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.creativedeveloper.ecommerce.AppConstants;

import javax.inject.Inject;

public class SessionManager {
    private Context context;

    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    @Inject
    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public static String getKeyUsername() {
        return KEY_USERNAME;
    }

    public static String getKeyPassword() {
        return KEY_PASSWORD;
    }

    public static String getKeyRole() {
        return KEY_ROLE;
    }

    public static String getKeyName() {
        return KEY_NAME;
    }

    public void createLoginSession(String name, String password, String role, String username) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_USERNAME, username);

        editor.commit();
    }

    private static final String KEY_USERNAME= "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ROLE = "role";
    private static final String KEY_NAME = "name";
}
