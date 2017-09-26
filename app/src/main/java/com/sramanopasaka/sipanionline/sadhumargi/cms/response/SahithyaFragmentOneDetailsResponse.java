package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.SahithyaFragmentOneWebview;

import java.util.ArrayList;

/**
 * Created by sipani001 on 20/9/17.
 */

public class SahithyaFragmentOneDetailsResponse extends GUIResponse {

    ArrayList<SahithyaFragmentOneWebview> pages=null;

    public ArrayList<SahithyaFragmentOneWebview> getPages() {
        return pages;
    }

    public void setPages(ArrayList<SahithyaFragmentOneWebview> pages) {
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
