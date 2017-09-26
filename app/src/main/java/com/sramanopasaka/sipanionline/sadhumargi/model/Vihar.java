package com.sramanopasaka.sipanionline.sadhumargi.model;

import java.util.Comparator;

/**
 * Created by sipani001 on 21/9/17.
 */

public class Vihar implements Comparator {

    private String si_no=null;
    private String guru_name=null;
    private String guru_assist_name=null;
    private String guru_location=null;
    private String guru_attender_name=null;
    private String guru_chief_attender=null;
    private String guru_dis=null;

    public String getGuru_dis() {
        return guru_dis;
    }

    public void setGuru_dis(String guru_dis) {
        this.guru_dis = guru_dis;
    }

    public String getSi_no() {
        return si_no;
    }

    public void setSi_no(String si_no) {
        this.si_no = si_no;
    }

    public String getGuru_name() {
        return guru_name;
    }

    public void setGuru_name(String guru_name) {
        this.guru_name = guru_name;
    }

    public String getGuru_assist_name() {
        return guru_assist_name;
    }

    public void setGuru_assist_name(String guru_assist_name) {
        this.guru_assist_name = guru_assist_name;
    }

    public String getGuru_location() {
        return guru_location;
    }

    public void setGuru_location(String guru_location) {
        this.guru_location = guru_location;
    }

    public String getGuru_attender_name() {
        return guru_attender_name;
    }

    public void setGuru_attender_name(String guru_attender_name) {
        this.guru_attender_name = guru_attender_name;
    }

    public String getGuru_chief_attender() {
        return guru_chief_attender;
    }

    public void setGuru_chief_attender(String guru_chief_attender) {
        this.guru_chief_attender = guru_chief_attender;
    }

    public String getGuru_phone() {
        return guru_phone;
    }

    public void setGuru_phone(String guru_phone) {
        this.guru_phone = guru_phone;
    }

    public String getGuru_att_phone() {
        return guru_att_phone;
    }

    public void setGuru_att_phone(String guru_att_phone) {
        this.guru_att_phone = guru_att_phone;
    }

    public String getGuru_lat() {
        return guru_lat;
    }

    public void setGuru_lat(String guru_lat) {
        this.guru_lat = guru_lat;
    }

    public String getGuru_lng() {
        return guru_lng;
    }

    public void setGuru_lng(String guru_lng) {
        this.guru_lng = guru_lng;
    }

    private String guru_phone=null;
    private String guru_att_phone=null;
    private String guru_lat=null;
    private String guru_lng=null;

    @Override
    public int compare(Object o, Object t1) {
        return 0;
    }
}
