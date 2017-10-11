package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Created by sipani001 on 20/9/17.
 */

public class EbookDetails {

    private String ebk_id=null;
    private String book_id=null;
    private String book_name=null;

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

    public String getEbook_parent_id() {
        return ebook_parent_id;
    }

    public void setEbook_parent_id(String ebook_parent_id) {
        this.ebook_parent_id = ebook_parent_id;
    }

    private String txt_file=null;
    private String date=null;
    private String ebook_parent_id=null;

}
