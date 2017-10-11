package com.sramanopasaka.sipanionline.sadhumargi;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;

/**
 * Created by sipani001 on 16/8/17.
 */

public class PasswordRecoverResponse extends GUIResponse{

    private boolean email;

    public boolean getEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

    public boolean getMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    private boolean mobile;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;



    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean status) {

    }
}
