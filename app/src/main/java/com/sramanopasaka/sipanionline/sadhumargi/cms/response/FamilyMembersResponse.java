package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.FamilyMembers;

import java.util.List;

/**
 * Created by sipani001 on 28/8/17.
 */

public class FamilyMembersResponse extends GUIResponse {

    private String result=null;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFamily_id() {
        return family_id;
    }

    public void setFamily_id(String family_id) {
        this.family_id = family_id;
    }

    public String getNum_members() {
        return num_members;
    }

    public void setNum_members(String num_members) {
        this.num_members = num_members;
    }

    public List<FamilyMembers> getMembers() {
        return members;
    }

    public void setMembers(List<FamilyMembers> members) {
        this.members = members;
    }

    private String family_id=null;
    private String num_members=null;
    private List<FamilyMembers> members=null;

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean status) {

    }
}
