package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Name    :   pranavjdev
 * Date   : 8/17/17
 * Email : pranavjaydev@gmail.com
 */

public class Education {


    public Education(String education_name, String education_description, String education_score, String education_institute, String education_year) {
        this.education_name = education_name;
        this.education_description = education_description;
        this.education_score = education_score;
        this.education_institute = education_institute;
        this.education_year = education_year;
    }

    private String id = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String education_name = null;

    private String education_description = null;

    private String education_score = null;

    private String education_institute = null;

    private String education_year = null;

    public String getEducationName() {
        return education_name;
    }

    public void setEducationName(String educationName) {
        this.education_name = educationName;
    }

    public String getDescription() {
        return education_description;
    }

    public void setDescription(String description) {
        this.education_description = description;
    }

    public String getScore() {
        return education_score;
    }

    public void setScore(String score) {
        this.education_score = score;
    }

    public String getIstitute() {
        return education_institute;
    }

    public void setIstitute(String istitute) {
        this.education_institute = istitute;
    }

    public String getYear() {
        return education_year;
    }

    public void setYear(String year) {
        this.education_year = year;
    }
}
