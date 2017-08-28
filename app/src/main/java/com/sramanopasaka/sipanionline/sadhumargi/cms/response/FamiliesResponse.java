package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.Family;
import com.sramanopasaka.sipanionline.sadhumargi.model.Relations;

import java.util.List;

/**
 * Created by sipani001 on 28/8/17.
 */

public class FamiliesResponse extends GUIResponse {

    private boolean statusCode = false;

    private String head_of_family = null;

    public String getHead_of_family() {
        return head_of_family;
    }

    public void setHead_of_family(String head_of_family) {
        this.head_of_family = head_of_family;
    }

    public boolean isStatusCode() {
        return statusCode;
    }

    public void setStatusCode(boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



    private String result=null;

    private List<Relations> relations = null;

    public List<Relations> getRelations() {
        return relations;
    }

    public void setRelations(List<Relations> relations) {
        this.relations = relations;
    }

    public List<Family> getFamilies() {
        return families;
    }

    public void setFamilies(List<Family> families) {
        this.families = families;
    }

    private List<Family> families;

    @Override
    public boolean isStatus() {
        return statusCode;
    }

    @Override
    public void setStatus(boolean status) {

        this.statusCode = status;

    }
}
