package com.sramanopasaka.sipanionline.sadhumargi.model;

import com.sramanopasaka.sipanionline.sadhumargi.listener.Listable;

/**
 * Created by sipani001 on 17/8/17.
 */

public class City implements Listable {


    String city_id;
    String city_name;

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getAnchal_id() {
        return anchal_id;
    }

    public void setAnchal_id(String anchal_id) {
        this.anchal_id = anchal_id;
    }

    String state_id;
    String anchal_id;

    @Override
    public String getLabel() {
        return city_name;
    }
}
