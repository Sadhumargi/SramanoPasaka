package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Created by sipani001 on 22/9/17.
 */

public class DonationsDetails {

    private String donate_id=null;

    public String getDonate_id() {
        return donate_id;
    }

    public void setDonate_id(String donate_id) {
        this.donate_id = donate_id;
    }

    public String getDonate_name() {
        return donate_name;
    }

    public void setDonate_name(String donate_name) {
        this.donate_name = donate_name;
    }

    public String getDonate_details() {
        return donate_details;
    }

    public void setDonate_details(String donate_details) {
        this.donate_details = donate_details;
    }

    public String getDonate_amount() {
        return donate_amount;
    }

    public void setDonate_amount(String donate_amount) {
        this.donate_amount = donate_amount;
    }

    private String donate_name=null;
    private String donate_details=null;
    private String donate_amount=null;
}
