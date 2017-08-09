package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.DatabaseHelper;

/**
 * Created by sipani001 on 27/9/16.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;
    private DatabaseHelper dbHelper;
    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
    public DatabaseHelper getDBHepler() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return dbHelper;
    }

    public void closeDB() {
        if (dbHelper != null) {
            dbHelper.close();
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

}