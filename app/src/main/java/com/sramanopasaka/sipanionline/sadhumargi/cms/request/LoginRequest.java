package com.sramanopasaka.sipanionline.sadhumargi.cms.request;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Name    :   pranavjdev
 * Date   : 8/9/17
 * Email : pranavjaydev@gmail.com
 */

public class LoginRequest extends GUIRequest {

    private String email = null;

    private String password = null;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    private String mobileNumber = null;


    @Override
    public JSONObject getURLEncodedPostdata() {
        JSONObject object = new JSONObject();
        try {
            if (!TextUtils.isEmpty(email))
                object.put("email_address", email);
            object.put("password", password);
            if (!TextUtils.isEmpty(mobileNumber))
                object.put("mobile", mobileNumber);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
