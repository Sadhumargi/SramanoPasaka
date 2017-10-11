package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.Vihar;

import java.util.ArrayList;

/**
 * Created by sipani001 on 21/9/17.
 */

public class ViharResponse extends GUIResponse {

    ArrayList<Vihar>data;

    public ArrayList<Vihar> getData() {
        return data;
    }

    public void setData(ArrayList<Vihar> data) {
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
