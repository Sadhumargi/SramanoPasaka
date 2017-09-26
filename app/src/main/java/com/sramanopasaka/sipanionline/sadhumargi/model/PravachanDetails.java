package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Created by sipani001 on 18/9/17.
 */

public class PravachanDetails {

    private String ebk_id=null;
    private String book_id=null;
    private String book_name=null;
    private String txt_file=null;
    private String date=null;
    private String pravachan_parent_id=null;

    public String getEbk_id() {
        return ebk_id;
    }

    public void setEbk_id(String ebk_id) {
        this.ebk_id = ebk_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getTxt_file() {
        return txt_file;
    }

    public void setTxt_file(String txt_file) {
        this.txt_file = txt_file;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPravachan_parent_id() {
        return pravachan_parent_id;
    }

    public void setPravachan_parent_id(String pravachan_parent_id) {
        this.pravachan_parent_id = pravachan_parent_id;
    }
}
