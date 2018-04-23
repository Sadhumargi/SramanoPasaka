package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.GathividhiModel;

import java.util.ArrayList;

/**
 * Created by apple on 12/01/18.
 */

public class GathividhiResponse extends  GUIResponse {

    private boolean statusCode = false;

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

    public ArrayList<GathividhiModel> getData() {
        return data;
    }

    public void setData(ArrayList<GathividhiModel> data) {
        this.data = data;
    }

    private String status = null;

    private ArrayList<GathividhiModel> data = null;

    @Override
    public boolean isStatus() {
        return statusCode;
    }

    @Override
    public void setStatus(boolean status) {

        this.statusCode=status;

    }
}
