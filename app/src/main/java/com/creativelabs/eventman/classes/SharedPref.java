package com.creativelabs.eventman.classes;

import android.content.Context;
import android.content.SharedPreferences;

import com.creativelabs.eventman.utils.Constants;

public class SharedPref {

    private SharedPreferences preferences;


    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE);
    }

    public static void setIsLoggedIn(Context context, boolean isLoggedIn) {
        getPreferences(context).edit().putBoolean(Constants.IS_LOGGED_IN, isLoggedIn).apply();
    }

    public static boolean getIsLoggedIn(Context context) {
        return getPreferences(context).getBoolean(Constants.IS_LOGGED_IN, false);
    }

}
