package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.CalenderModel;

import java.util.List;

public class CalenderResponse extends GUIResponse {

    private String status = null;
    private boolean statusCode = false;
    private List<CalenderModel> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isStatusCode() {
        return statusCode;
    }

    public void setStatusCode(boolean statusCode) {
        this.statusCode = statusCode;
    }

    public List<CalenderModel> getData() {
        return data;
    }

    public void setData(List<CalenderModel> data) {
        this.data = data;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean status) {

    }
}
