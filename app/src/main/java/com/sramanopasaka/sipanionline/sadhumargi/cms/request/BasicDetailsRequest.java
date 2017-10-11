package com.sramanopasaka.sipanionline.sadhumargi.cms.request;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Name    :   pranavjdev
 * Date   : 8/9/17
 * Email : pranavjaydev@gmail.com
 */

public class BasicDetailsRequest extends GUIRequest {

    private String memberId = null;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    private String appToken = null;


    @Override
    public JSONObject getURLEncodedPostdata() {
        JSONObject object = new JSONObject();
        try {
            object.put("member_id", memberId);
            object.put("app_token", appToken);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
