package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.Ebook;

import java.util.ArrayList;

/**
 * Created by sipani001 on 14/9/17.
 */

public class EbookResponse extends GUIResponse {

    private boolean statusCode = false;
    private String status = null;

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

    public ArrayList<Ebook> getData() {
        return data;
    }

    public void setData(ArrayList<Ebook> data) {
        this.data = data;
    }

    private ArrayList<Ebook> data =null;

    @Override
    public boolean isStatus() {
        return statusCode;
    }

    @Override
    public void setStatus(boolean status) {
        this.statusCode=status;

    }
}
