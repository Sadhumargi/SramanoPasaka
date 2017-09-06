package com.sramanopasaka.sipanionline.sadhumargi.model;


import com.sramanopasaka.sipanionline.sadhumargi.listener.Listable;

/**
 * Created by sipani001 on 28/8/17.
 */

public class Zone implements Listable{

    private String anchal_id=null;

    public String getAnchal_id() {
        return anchal_id;
    }

    public void setAnchal_id(String anchal_id) {
        this.anchal_id = anchal_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(String display_order) {
        this.display_order = display_order;
    }

    private String name=null;
    private String display_order=null;
    @Override
    public String getLabel() {
        return name;
    }
}
