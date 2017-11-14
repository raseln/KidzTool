package com.raselahmed.kidztool.data_access;

import android.content.Context;
import android.content.SharedPreferences;

public class SpHandler {

    private static final String DB = "KidData";
    private SharedPreferences preferences;

    public SpHandler(Context context) {
        preferences = context.getSharedPreferences(DB, Context.MODE_PRIVATE);
    }

    public void saveStatus(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("LoggedIn", true);
        editor.apply();
    }

    public boolean getStatus(){
        return preferences.getBoolean("LoggedIn", false);
    }
}
