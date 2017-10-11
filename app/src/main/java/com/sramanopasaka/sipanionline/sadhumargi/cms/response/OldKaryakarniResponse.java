package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.OldKaryakarniModel;

import java.util.ArrayList;

/**
 * Created by sipani001 on 25/9/17.
 */

public class OldKaryakarniResponse extends GUIResponse {

    private ArrayList<OldKaryakarniModel> pages=null;

    public ArrayList<OldKaryakarniModel> getPages() {
        return pages;
    }

    public void setPages(ArrayList<OldKaryakarniModel> pages) {
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
