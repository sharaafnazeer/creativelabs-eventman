package com.creativelabs.eventman.classes;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private SharedPreferences preferences;
    private static final int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "eventMan";
    private static final String IS_LOGGED_IN = "is_logged_in";

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    public static void setIsLoggedIn(Context context, boolean isLoggedIn) {
        getPreferences(context).edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply();
    }

    public static boolean getIsLoggedIn(Context context) {
        return getPreferences(context).getBoolean(IS_LOGGED_IN, false);
    }

}
