package com.sramanopasaka.sipanionline.sadhumargi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by Pranav J.Dev
 * Email : pranavjaydev@gmail.com
 * on 27-09-2016.
 */
public class PreferenceUtils {

    private static final String PREFERNCE_NAME = "sadhumargi";

    private static final String PREFERECE_KEY_PASSWORD = "password";


    public static void setPassword(Context context,String password) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE).edit();
        editor.putString(PREFERECE_KEY_PASSWORD, password);
        editor.commit();
    }

    public static String getPassword(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE);
        return prefs.getString(PREFERECE_KEY_PASSWORD, null);
    }


}