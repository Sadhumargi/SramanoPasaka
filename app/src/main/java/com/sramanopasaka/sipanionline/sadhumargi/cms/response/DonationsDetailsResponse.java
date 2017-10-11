package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.DonationsDetails;

import java.util.ArrayList;

/**
 * Created by sipani001 on 22/9/17.
 */

public class DonationsDetailsResponse extends GUIResponse {

    private ArrayList<DonationsDetails> data=null;

    public ArrayList<DonationsDetails> getData() {
        return data;
    }

    public void setData(ArrayList<DonationsDetails> data) {
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
