package com.sramanopasaka.sipanionline.sadhumargi.helpers;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.CityListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LocalSanghResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.RegisterResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.StateListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.ZoneListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.model.DharmicData;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.model.SanghData;

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


    public static void saveSanghResponse(SanghData response) {
        saveOfflineData("sangh", response);
    }


    public static SanghData getSanghData() {
        String offlineData = getOfflineData("sangh");
        if (offlineData != null) {
            return getStringToObject(offlineData, SanghData.class);
        } else {
            return null;
        }
    }
    public static void deleteSanghResponse() {
        deleteOfflineData("sangh");
    }

    public static void saveStateResponse(StateListResponse response) {
        saveOfflineData("state", response);
    }


    public static StateListResponse getStateList() {
        String offlineData = getOfflineData("state");
        if (offlineData != null) {
            return getStringToObject(offlineData, StateListResponse.class);
        } else {
            return null;
        }
    }
    public static void saveCityResponse(CityListResponse response) {
        saveOfflineData("city", response);
    }


    public static CityListResponse getCityList() {
        String offlineData = getOfflineData("city");
        if (offlineData != null) {
            return getStringToObject(offlineData, CityListResponse.class);
        } else {
            return null;
        }
    }

    public static void saveZoneResponse(ZoneListResponse response) {
        saveOfflineData("zone", response);
    }


    public static ZoneListResponse getZoneList() {
        String offlineData = getOfflineData("zone");
        if (offlineData != null) {
            return getStringToObject(offlineData, ZoneListResponse.class);
        } else {
            return null;
        }
    }

    public static void saveLocalSanghResponse(LocalSanghResponse response) {
        saveOfflineData("localSangh", response);
    }


    public static LocalSanghResponse getLocalSanghList() {
        String offlineData = getOfflineData("localSangh");
        if (offlineData != null) {
            return getStringToObject(offlineData, LocalSanghResponse.class);
        } else {
            return null;
        }
    }

}