package com.sramanopasaka.sipanionline.sadhumargi.utils;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

/**
 * Created by apple on 10/04/18.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String  REG_TOKEN = "REG_TOKEN";

    @Override
    public void onTokenRefresh() {

        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);

    }
}
