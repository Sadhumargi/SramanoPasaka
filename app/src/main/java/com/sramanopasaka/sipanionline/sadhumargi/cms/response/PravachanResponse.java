package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.Pravachan;

import java.util.ArrayList;

/**
 * Created by sipani001 on 18/9/17.
 */

public class PravachanResponse extends GUIResponse {

    private boolean statusCode=false;
    private String status=null;
    private ArrayList<Pravachan> data;

    public boolean getStatusCode() {
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

    public ArrayList<Pravachan> getData() {
        return data;
    }

    public void setData(ArrayList<Pravachan> data) {
        this.data = data;
    }

    @Override
    public boolean isStatus() {
        return statusCode;
    }

    @Override
    public void setStatus(boolean status) {
        this.statusCode=status;

    }
}
