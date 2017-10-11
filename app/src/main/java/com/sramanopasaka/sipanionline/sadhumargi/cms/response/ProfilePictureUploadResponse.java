package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

/**
 * Name    :   pranavjdev
 * Date   : 9/11/17
 * Email : pranavjaydev@gmail.com
 */

public class ProfilePictureUploadResponse extends GUIResponse {
    private boolean statusCode = false;
    @Override
    public boolean isStatus() {
        return statusCode;
    }

    @Override
    public void setStatus(boolean status) {
        this.statusCode = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status = null;


}
