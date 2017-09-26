package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.EbookDetails;

import java.util.ArrayList;

/**
 * Created by sipani001 on 20/9/17.
 */

public class EbookDetailsResponse extends GUIResponse {

    private ArrayList<EbookDetails> pages=null;


    public ArrayList<EbookDetails> getPages() {
        return pages;
    }

    public void setPages(ArrayList<EbookDetails> pages) {
        this.pages = pages;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean status) {

    }
}
