package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

/**
 * Created by sipani001 on 22/8/17.
 */

public class UpdateHobbiesResponse extends GUIResponse {

    private boolean statusCode;
    private String status=null;

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

    private String message=null;


    @Override
    public boolean isStatus() {
        return statusCode;
    }

    @Override
    public void setStatus(boolean status) {

    }
}
