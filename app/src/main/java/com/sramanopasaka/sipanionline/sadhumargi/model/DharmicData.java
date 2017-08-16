package com.sramanopasaka.sipanionline.sadhumargi.model;

import java.util.List;

/**
 * Name    :   pranavjdev
 * Date   : 8/16/17
 * Email : pranavjaydev@gmail.com
 */

public class DharmicData {

    private List<Exams> exams = null;

    private Knowledge knowledge = null;

    private Promise promises = null;

    public List<Exams> getExams() {
        return exams;
    }

    public void setExams(List<Exams> exams) {
        this.exams = exams;
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }

    public Promise getPromises() {
        return promises;
    }

    public void setPromises(Promise promises) {
        this.promises = promises;
    }
}
