package com.sramanopasaka.sipanionline.sadhumargi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apple on 10/01/18.
 */

public class GathividhiModel {

    private String text_id=null;
    private String text_news_title=null;
    private String text_news_details=null;

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

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc_type() {
        return desc_type;
    }

    public void setDesc_type(String desc_type) {
        this.desc_type = desc_type;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public String getImg_text_title() {
        return img_text_title;
    }

    public void setImg_text_title(String img_text_title) {
        this.img_text_title = img_text_title;
    }

    public String getImg_text() {
        return img_text;
    }

    public void setImg_text(String img_text) {
        this.img_text = img_text;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    private  String date = null;
    private  String created_date = null;
    private  int type ;
    private  String desc_type = null;

    private String img_id=null;
    private String img_link=null;
    private String img_text_title=null;
    private String img_text=null;

    private  String video_id = null;
    private  String video_link = null;
    private  String video_title = null;

}
