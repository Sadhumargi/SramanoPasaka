package com.sramanopasaka.sipanionline.sadhumargi.helpers;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.RegisterResponse;
import com.sramanopasaka.sipanionline.sadhumargi.model.DharmicData;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

public class OfflineData extends BaseOffline {


    public static void saveLoginResponse(LoginModel response) {
        saveOfflineData("login", response);
    }


    public static LoginModel getLoginData() {
        String offlineData = getOfflineData("login");
        if (offlineData != null) {
            return getStringToObject(offlineData, LoginModel.class);
        } else {
            return null;
        }
    }

    public static void deleteLoginResponse() {
        deleteOfflineData("login");
    }


    public static void saveDharmicResponse(DharmicData response) {
        saveOfflineData("dharmik", response);
    }


    public static DharmicData getDharmikData() {
        String offlineData = getOfflineData("dharmik");
        if (offlineData != null) {
            return getStringToObject(offlineData, DharmicData.class);
        } else {
            return null;
        }
    }

    public static void deleteDharmikResponse() {
        deleteOfflineData("dharmik");
    }
}