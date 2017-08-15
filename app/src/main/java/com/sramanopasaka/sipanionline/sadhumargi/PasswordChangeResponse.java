package com.sramanopasaka.sipanionline.sadhumargi;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;

/**
 * Created by sipani001 on 15/8/17.
 */

public class PasswordChangeResponse extends GUIResponse{

    private String status;
    private String message;

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

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean status) {

    }
}
