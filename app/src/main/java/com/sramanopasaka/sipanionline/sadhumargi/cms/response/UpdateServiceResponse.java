package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

/**
 * Created by sipani001 on 21/8/17.
 */

public class UpdateServiceResponse extends GUIResponse{

    private boolean statusCode = false;
    private String status = null;
    private String message = null;

    public boolean isStatusCode() {
        return statusCode;
    }

    public void setStatusCode(boolean statusCode) {
        this.statusCode = statusCode;
    }

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
        return statusCode;
    }

    @Override
    public void setStatus(boolean status) {

    }
}
