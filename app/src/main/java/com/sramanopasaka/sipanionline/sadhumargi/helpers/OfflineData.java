package com.sramanopasaka.sipanionline.sadhumargi.helpers;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;

public class OfflineData extends BaseOffline {


    public static void saveLoginResponse(LoginResponse response) {
        saveOfflineData("login", response);
    }

    public static LoginResponse getLoginData() {
        String offlineData = getOfflineData("login");
        if (offlineData != null) {
            return getStringToObject(offlineData, LoginResponse.class);
        } else {
            return null;
        }
    }

    public static void deleteLoginResponse(){
        deleteOfflineData("login");
    }
}