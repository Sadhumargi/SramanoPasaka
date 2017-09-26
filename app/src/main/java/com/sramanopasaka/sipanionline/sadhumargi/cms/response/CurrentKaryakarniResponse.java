package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.CurrentKaryakarniModel;

import java.util.ArrayList;

/**
 * Created by sipani001 on 22/9/17.
 */

public class CurrentKaryakarniResponse extends GUIResponse {

    private ArrayList<CurrentKaryakarniModel> pages;

    public ArrayList<CurrentKaryakarniModel> getPages() {
        return pages;
    }

    public void setPages(ArrayList<CurrentKaryakarniModel> pages) {
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
