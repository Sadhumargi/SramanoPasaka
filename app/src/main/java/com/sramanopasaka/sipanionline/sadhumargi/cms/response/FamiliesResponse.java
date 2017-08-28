package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.Families;

import java.util.List;

/**
 * Created by sipani001 on 28/8/17.
 */

public class FamiliesResponse extends GUIResponse {

    private boolean statusCode = false;

    public boolean isStatusCode() {
        return statusCode;
    }

    public void setStatusCode(boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Families> getData() {
        return data;
    }

    public void setData(List<Families> data) {
        this.data = data;
    }

    private String result=null;
    private List<Families> data;

    @Override
    public boolean isStatus() {
        return statusCode;
    }

    @Override
    public void setStatus(boolean status) {

        this.statusCode = status;

    }
}
