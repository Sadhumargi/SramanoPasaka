package com.sramanopasaka.sipanionline.sadhumargi;

import java.io.Serializable;

/**
 * Created by SipaniOnline on 05-07-2016.
 */
public class EbookGetSetter implements Serializable
{
    private String imglink;
    private String date;
    private String id;

    public String getId() {
        return id;
    }

    public String getDate() {

        return date;
    }

    public String getImglink() {

        return imglink;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImglink(String imglink) {

        this.imglink = imglink;
    }

    public void setDate(String date) {

        this.date = date;
    }
}
