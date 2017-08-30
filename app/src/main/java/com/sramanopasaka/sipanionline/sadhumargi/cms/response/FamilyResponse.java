package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.Family;
import com.sramanopasaka.sipanionline.sadhumargi.model.Relations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sipani001 on 28/8/17.
 */

public class FamilyResponse extends GUIResponse {

    private boolean statusCode = false;

    public boolean isHead_of_family() {
        return head_of_family;
    }

    public void setHead_of_family(boolean head_of_family) {
        this.head_of_family = head_of_family;
    }

    private boolean head_of_family = false;


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

    private ArrayList<Relations> relations = null;

    public ArrayList<Relations> getRelations() {
        return relations;
    }

    public void setRelations(ArrayList<Relations> relations) {
        this.relations = relations;
    }

    public ArrayList<Family> getFamilies() {
        return families;
    }

    public void setFamilies(ArrayList<Family> families) {
        this.families = families;
    }

    private ArrayList<Family> families;

    @Override
    public boolean isStatus() {
        return statusCode;
    }

    @Override
    public void setStatus(boolean status) {

        this.statusCode = status;

    }
}
