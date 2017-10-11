package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Created by sipani001 on 25/9/17.
 */

public class GathividhiTextNews {

    private String text_id=null;
    private String text_news_title=null;
    private String text_news_details=null;
    private String date=null;

    public String getText_id() {
        return text_id;
    }

    public void setText_id(String text_id) {
        this.text_id = text_id;
    }

    public String getText_news_title() {
        return text_news_title;
    }

    public void setText_news_title(String text_news_title) {
        this.text_news_title = text_news_title;
    }

    public String getText_news_details() {
        return text_news_details;
    }

    public void setText_news_details(String text_news_details) {
        this.text_news_details = text_news_details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
