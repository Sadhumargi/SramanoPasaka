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

    private static final String PREFERECE_KEY_TOKEN = "token";

    private static final String PREFERECE_KEY_ID = "id";

    private static final String KEY_LAST_LOGIN_TIME = "last_login_time";



    public static void setPassword(Context context,String password) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE).edit();
        editor.putString(PREFERECE_KEY_PASSWORD, password);
        editor.commit();
    }

    public static String getPassword(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE);
        return prefs.getString(PREFERECE_KEY_PASSWORD, null);
    }

    public static void setUserId(Context context,int password) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE).edit();
        editor.putInt(PREFERECE_KEY_ID, password);
        editor.commit();
    }

    public static int getUserId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE);
        return prefs.getInt(PREFERECE_KEY_ID, 0);
    }

    public static void setAppToken(Context context,String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE).edit();
        editor.putString(PREFERECE_KEY_TOKEN, token);
        editor.commit();
    }

    public static String getAppToken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE);
        return prefs.getString(PREFERECE_KEY_TOKEN, null);
    }



    public static void setLastLoginTime(Context context, long millisecs) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE).edit();
        editor.putLong(KEY_LAST_LOGIN_TIME , millisecs);
        editor.commit();
    }

    public static long getLastLoginTime(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE);
        return prefs.getLong(KEY_LAST_LOGIN_TIME , 0);
    }

}