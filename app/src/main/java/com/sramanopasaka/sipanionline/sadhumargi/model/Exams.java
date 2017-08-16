package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Name    :   pranavjdev
 * Date   : 8/16/17
 * Email : pranavjaydev@gmail.com
 */

public class Exams {


    private String id = null;

    private String member_id = null;

    private String exam_name = null;

    private String exam_institute_name = null;

    private String exam_year = null;

    private String exam_comments = null;

    private String _added_on = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getExam_institute_name() {
        return exam_institute_name;
    }

    public void setExam_institute_name(String exam_institute_name) {
        this.exam_institute_name = exam_institute_name;
    }

    public String getExam_year() {
        return exam_year;
    }

    public void setExam_year(String exam_year) {
        this.exam_year = exam_year;
    }

    public String getExam_comments() {
        return exam_comments;
    }

    public void setExam_comments(String exam_comments) {
        this.exam_comments = exam_comments;
    }

    public String get_added_on() {
        return _added_on;
    }

    public void set_added_on(String _added_on) {
        this._added_on = _added_on;
    }
}
