package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.RamSahitya;

import java.util.ArrayList;

/**
 * Created by sipani001 on 21/9/17.
 */

public class RamSahityaResponse  extends GUIResponse {

    private ArrayList<RamSahitya> data;

    public ArrayList<RamSahitya> getData() {
        return data;
    }

    public void setData(ArrayList<RamSahitya> data) {
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
