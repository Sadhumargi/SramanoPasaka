package com.sramanopasaka.sipanionline.sadhumargi.model;

import com.sramanopasaka.sipanionline.sadhumargi.listener.Listable;

/**
 * Name    :   pranavjdev
 * Date   : 8/28/17
 * Email : pranavjaydev@gmail.com
 */

public class Relations implements Listable {

    String relation_id=null;
    String relation_label_en=null;
    String relation_label_hi=null;
    private String relation = null;
    private String id = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelation() {

        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(String relation_id) {
        this.relation_id = relation_id;
    }

    public String getRelation_label_en() {
        return relation_label_en;
    }

    public void setRelation_label_en(String relation_label_en) {
        this.relation_label_en = relation_label_en;
    }

    public String getRelation_label_hi() {
        return relation_label_hi;
    }

    public void setRelation_label_hi(String relation_label_hi) {
        this.relation_label_hi = relation_label_hi;
    }

    @Override
    public String getLabel() {
        return relation;
    }
}
