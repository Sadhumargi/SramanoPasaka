package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.PravachanDetails;

import java.util.ArrayList;

/**
 * Created by sipani001 on 18/9/17.
 */

public class PravachanDetailsResponse extends GUIResponse{

    private boolean statusCode=false;

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

    private String status =null;



    public ArrayList<PravachanDetails> getPages() {
        return pages;
    }

    public void setPages(ArrayList<PravachanDetails> pages) {
        this.pages = pages;
    }

    private ArrayList<PravachanDetails> pages;


    @Override
    public boolean isStatus() {
        return statusCode;
    }

    @Override
    public void setStatus(boolean status) {

        this.statusCode=status;

    }
}