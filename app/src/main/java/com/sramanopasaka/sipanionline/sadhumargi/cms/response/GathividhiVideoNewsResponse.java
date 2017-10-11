package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.GathividhiVideoNews;

import java.util.ArrayList;

/**
 * Created by sipani001 on 25/9/17.
 */

public class GathividhiVideoNewsResponse extends GUIResponse {

    private ArrayList<GathividhiVideoNews> data=null;

    public ArrayList<GathividhiVideoNews> getData() {
        return data;
    }

    public void setData(ArrayList<GathividhiVideoNews> data) {
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
