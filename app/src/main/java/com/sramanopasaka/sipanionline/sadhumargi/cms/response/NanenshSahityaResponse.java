package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.NanenshSahitya;

import java.util.ArrayList;

/**
 * Created by sipani001 on 21/9/17.
 */

public class NanenshSahityaResponse extends GUIResponse {


    private ArrayList<NanenshSahitya> data=null;

    public ArrayList<NanenshSahitya> getData() {
        return data;
    }

    public void setData(ArrayList<NanenshSahitya> data) {
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
