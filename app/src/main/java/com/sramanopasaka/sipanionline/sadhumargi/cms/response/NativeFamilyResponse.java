package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.Family;
import com.sramanopasaka.sipanionline.sadhumargi.model.Relations;

import java.util.List;

/**
 * Created by sipani001 on 28/8/17.
 */

public class NativeFamilyResponse extends GUIResponse{


    Boolean statusCode=false;
    @Override
    public boolean isStatus() {
        return statusCode;
    }

    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Family> getFamilies() {
        return families;
    }

    public void setFamilies(List<Family> families) {
        this.families = families;
    }

    public List<Relations> getRelations() {
        return relations;
    }

    public void setRelations(List<Relations> relations) {
        this.relations = relations;
    }

    @Override
    public void setStatus(boolean status) {
        this.statusCode=status;

    }

    private String result=null;

    private List<Family> families;
    private List<Relations> relations;

}
