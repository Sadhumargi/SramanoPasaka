package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Created by sipani001 on 21/8/17.
 */

public class SocialRole {

    String id = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String start_date=null;
    String end_date=null;
    String social_org_name=null;
    String social_org_active=null;
    String social_org_role=null;
    String sangh_local_name = null;

    public String getSangh_local_name() {
        return sangh_local_name;
    }

    public void setSangh_local_name(String sangh_local_name) {
        this.sangh_local_name = sangh_local_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getSocial_org_name() {
        return social_org_name;
    }

    public void setSocial_org_name(String social_org_name) {
        this.social_org_name = social_org_name;
    }

    public String getSocial_org_active() {
        return social_org_active;
    }

    public void setSocial_org_active(String social_org_active) {
        this.social_org_active = social_org_active;
    }

    public String getSocial_org_role() {
        return social_org_role;
    }

    public void setSocial_org_role(String social_org_role) {
        this.social_org_role = social_org_role;
    }

    public String getSocial_org_role_level() {
        return social_org_role_level;
    }

    public void setSocial_org_role_level(String social_org_role_level) {
        this.social_org_role_level = social_org_role_level;
    }

    String social_org_role_level=null;



}
