package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.LocalSangh;

import java.util.List;

/**
 * Created by sipani001 on 29/8/17.
 */

public class LocalSanghResponse extends GUIResponse {

    private boolean statusCode=false;

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

    public List<LocalSangh> getData() {
        return data;
    }

    public void setData(List<LocalSangh> data) {
        this.data = data;
    }

    private String result=null;
    private List<LocalSangh> data;




    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean status) {

    }
}
