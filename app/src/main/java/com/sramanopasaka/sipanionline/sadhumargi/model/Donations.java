package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Created by sipani001 on 14/9/17.
 */

public class Donations {

    private String Donate_id=null;
    private String Types_donations=null;

    public Donations(int imageArray) {
        this.imageArray = imageArray;
    }

    private int imageArray = 0;

    public String getDonate_id() {
        return Donate_id;
    }

    public void setDonate_id(String donate_id) {
        Donate_id = donate_id;
    }

    public String getTypes_donations() {
        return Types_donations;
    }

    public void setTypes_donations(String types_donations) {
        Types_donations = types_donations;
    }
}
