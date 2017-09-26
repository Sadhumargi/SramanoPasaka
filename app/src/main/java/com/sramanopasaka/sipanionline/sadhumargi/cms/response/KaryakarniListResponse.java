package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.KaryakarniList;

import java.util.ArrayList;

/**
 * Created by sipani001 on 22/9/17.
 */

public class KaryakarniListResponse extends GUIResponse{

    private ArrayList<KaryakarniList> data;

    public ArrayList<KaryakarniList> getData() {
        return data;
    }

    public void setData(ArrayList<KaryakarniList> data) {
        this.data = data;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean status) {

    }
}
