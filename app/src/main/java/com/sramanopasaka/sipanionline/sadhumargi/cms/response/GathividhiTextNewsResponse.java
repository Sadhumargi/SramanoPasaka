package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.GathividhiTextNews;

import java.util.ArrayList;

/**
 * Created by sipani001 on 25/9/17.
 */

public class GathividhiTextNewsResponse extends GUIResponse {

    private ArrayList<GathividhiTextNews> data=null;

    public ArrayList<GathividhiTextNews> getData() {
        return data;
    }

    public void setData(ArrayList<GathividhiTextNews> data) {
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
