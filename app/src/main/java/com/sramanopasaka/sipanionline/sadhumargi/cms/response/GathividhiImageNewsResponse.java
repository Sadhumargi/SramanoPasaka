package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import java.util.ArrayList;

/**
 * Created by sipani001 on 25/9/17.
 */

public class GathividhiImageNewsResponse extends GUIResponse  {

    private ArrayList<com.sramanopasaka.sipanionline.sadhumargi.model.GathividhiImageNews> data=null;

    public ArrayList<com.sramanopasaka.sipanionline.sadhumargi.model.GathividhiImageNews> getData() {
        return data;
    }

    public void setData(ArrayList<com.sramanopasaka.sipanionline.sadhumargi.model.GathividhiImageNews> data) {
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
