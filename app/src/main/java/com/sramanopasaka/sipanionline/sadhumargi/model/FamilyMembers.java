package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Created by sipani001 on 28/8/17.
 */

public class FamilyMembers {

    private String member_id=null;
    private  boolean  is_head_of_family=false;
    private String first_name=null;
    private String middle_name=null;
    private String last_name=null;
    private boolean married=false;
    private String birth_day=null;
    private String relation_hof=null;
    private String mobile=null;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public boolean is_head_of_family() {
        return is_head_of_family;
    }

    public void setIs_head_of_family(boolean is_head_of_family) {
        this.is_head_of_family = is_head_of_family;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }

    public String getRelation_hof() {
        return relation_hof;
    }

    public void setRelation_hof(String relation_hof) {
        this.relation_hof = relation_hof;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
