package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Name    :   pranavjdev
 * Date   : 8/15/17
 * Email : pranavjaydev@gmail.com
 */

public class Business {

    public Business(String business_type, String business_name, String business_role, String business_start_year) {
        this.business_type = business_type;
        this.business_name = business_name;
        this.business_role = business_role;
        this.business_start_year = business_start_year;
    }

    private String id = null;

    private String business_type = null;

    private String business_name = null;

    private String business_role = null;

    private String business_start_year = null;

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBusiness_role() {
        return business_role;
    }

    public void setBusiness_role(String business_role) {
        this.business_role = business_role;
    }

    public String getBusiness_start_year() {
        return business_start_year;
    }

    public void setBusiness_start_year(String business_start_year) {
        this.business_start_year = business_start_year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
