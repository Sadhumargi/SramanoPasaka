package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.SahityaFragmentOne;

import java.util.ArrayList;

/**
 * Created by sipani001 on 20/9/17.
 */

public class SahithyaFragmentOneResponse extends GUIResponse {

    private ArrayList<SahityaFragmentOne> data;

    public ArrayList<SahityaFragmentOne> getData() {
        return data;
    }

    public void setData(ArrayList<SahityaFragmentOne> data) {
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
