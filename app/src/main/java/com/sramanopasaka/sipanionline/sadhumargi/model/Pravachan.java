package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Created by sipani001 on 18/9/17.
 */

public class Pravachan {

    private String prv_id=null;

    public String getPrv_id() {
        return prv_id;
    }

    public void setPrv_id(String prv_id) {
        this.prv_id = prv_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String title=null;
    private String date=null;

}
