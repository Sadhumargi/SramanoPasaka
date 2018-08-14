package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.utils.PreferenceUtils;

public class Splash extends BaseActivity implements GUICallback{


    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*if (!com.sramanopasaka.sipanionline.sadhumargi.utils.Utils.needToCallApi(PreferenceUtils.getLastLoginTime(this), System.currentTimeMillis()) && (OfflineData.getLoginData()!=null)) {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setToken(PreferenceUtils.getAppToken(this));
            loginRequest.setId(String.valueOf(PreferenceUtils.getUserId(this)));
            JsonParser jsonParser = new JsonParser();
            JsonObject gsonObject = (JsonObject) jsonParser.parse(loginRequest.getURLEncodedPostdata().toString());

            RequestProcessor requestProcessor = new RequestProcessor(this);
            requestProcessor.doLoginWithToken(gsonObject);
        }else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                *//* Create an Intent that will start the Menu-Activity. *//*
                    Intent mainIntent = new Intent(Splash.this, MainActivityCollectionview.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this, MainActivityCollectionview.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.actionbar));
        }

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }


    @Override
    public void onRequestProcessed(GUIResponse guiResponse, GUICallback.RequestStatus requestStatus) {
        if (guiResponse != null) {
            if (requestStatus.equals(GUICallback.RequestStatus.SUCCESS)) {

                if (guiResponse instanceof LoginResponse) {
                    LoginResponse loginResponse = (LoginResponse) guiResponse;
                    if (loginResponse != null) {
                        if (!TextUtils.isEmpty(loginResponse.getStatus()) && loginResponse.getStatus().equalsIgnoreCase("success")) {
                            OfflineData.saveLoginResponse(loginResponse.getData());

                            PreferenceUtils.setLastLoginTime(Splash.this,System.currentTimeMillis());

                            Intent mainIntent = new Intent(Splash.this, MainActivityCollectionview.class);
                            startActivity(mainIntent);
                            finish();
                        } else {
                            if (!TextUtils.isEmpty(loginResponse.getMessage())) {
                                Toast.makeText(Splash.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Splash.this, "Invalid username/password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }

        }

    }

}
