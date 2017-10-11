package com.sramanopasaka.sipanionline.sadhumargi.model;

import com.sramanopasaka.sipanionline.sadhumargi.listener.Listable;

/**
 * Created by sipani001 on 6/9/17.
 */

public class Gender implements Listable {

    private String data;

    public Gender(String data) {
        this.data = data;
    }

    @Override
    public String getLabel() {
        return data;
    }
}
