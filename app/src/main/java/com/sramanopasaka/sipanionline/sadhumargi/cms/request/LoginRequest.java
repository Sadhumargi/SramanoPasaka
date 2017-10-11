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

    private String id = null;

    private String token = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public JSONObject getURLEncodedPostdata() {
        JSONObject object = new JSONObject();
        try {
            object.put("email_address", email);
            object.put("password", password);
            object.put("mobile", mobileNumber);
            object.put("token", token);
            object.put("id", id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
