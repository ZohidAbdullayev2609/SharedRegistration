package com.example.sharedregistration.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    private static MySharedPreference mySharedPreference = new MySharedPreference();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static String UNICAL_KEY = "android_all";

    public static MySharedPreference getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(UNICAL_KEY, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return mySharedPreference;
    }

    public void setUserData(String userData) {
        editor.putString("user", userData);
        editor.commit();
    }

    public String getUserData() {
        return sharedPreferences.getString("user", "");
    }
}
