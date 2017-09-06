package com.sramanopasaka.sipanionline.sadhumargi.model;

import com.sramanopasaka.sipanionline.sadhumargi.listener.Listable;

/**
 * Created by sipani001 on 5/9/17.
 */

public class ProfileCreatedBy implements Listable {


    private String data =null;


    public ProfileCreatedBy(String data) {
        this.data = data;
    }

    @Override
    public String getLabel() {
        return data;
    }
}
