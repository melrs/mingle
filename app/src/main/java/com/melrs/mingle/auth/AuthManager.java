package com.melrs.mingle.auth;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthManager {
    private static final String PREFS_NAME = "AuthPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private final SharedPreferences sharedPreferences;

    public AuthManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void login(String username, String password) {
        // Adicione aqui a lógica de autenticação (ex: chamada a um servidor)
        // Se a autenticação for bem-sucedida:
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, true).apply();
    }

    public void logout() {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, false).apply();
    }
}