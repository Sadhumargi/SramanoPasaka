package com.sramanopasaka.sipanionline.sadhumargi.model;

import com.sramanopasaka.sipanionline.sadhumargi.listener.Listable;

/**
 * Name    :   pranavjdev
 * Date   : 9/5/17
 * Email : pranavjaydev@gmail.com
 */

public class Salutation implements Listable {

    public Salutation(String english, String hindi) {
        this.english = english;
        this.hindi = hindi;
    }

    private String english = null;

    private String hindi = null;

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getHindi() {
        return hindi;
    }

    public void setHindi(String hindi) {
        this.hindi = hindi;
    }

    @Override
    public String getLabel() {
        return english +"/"+hindi;
    }
}
