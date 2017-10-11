package com.sramanopasaka.sipanionline.sadhumargi;

import android.support.annotation.NonNull;

/**
 * Created by SipaniOnline on 06-07-2016.
 */
public class PravachanGetSetter implements Comparable<PravachanGetSetter>{

    private String title;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public String getPravachan_id() {
        return pravachan_id;
    }

    public void setPravachan_id(String pravachan_id) {
        this.pravachan_id = pravachan_id;
    }

    private String pravachan_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int compareTo(@NonNull PravachanGetSetter another) {

        return getDate().compareTo(another.getDate());
    }
}
