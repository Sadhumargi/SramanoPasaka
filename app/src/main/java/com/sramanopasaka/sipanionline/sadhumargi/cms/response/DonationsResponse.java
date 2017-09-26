package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.Donations;

import java.util.ArrayList;

/**
 * Created by sipani001 on 22/9/17.
 */

public class DonationsResponse extends GUIResponse {

    private ArrayList<Donations> data=null;

    public ArrayList<Donations> getData() {
        return data;
    }

    public void setData(ArrayList<Donations> data) {
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
