package com.sramanopasaka.sipanionline.sadhumargi.model;

import java.util.Comparator;

/**
 * Created by sipani001 on 22/9/17.
 */

public class KaryakarniList implements Comparator<KaryakarniList> {

    private String karyakarni_id=null;

    public String getKaryakarni_id() {
        return karyakarni_id;
    }

    public void setKaryakarni_id(String karyakarni_id) {
        this.karyakarni_id = karyakarni_id;
    }

    public String getLogo_id() {
        return logo_id;
    }

    public void setLogo_id(String logo_id) {
        this.logo_id = logo_id;
    }

    public String getGrp_karkarni_imglink() {
        return grp_karkarni_imglink;
    }

    public void setGrp_karkarni_imglink(String grp_karkarni_imglink) {
        this.grp_karkarni_imglink = grp_karkarni_imglink;
    }

    public String getGrp_karkarni_name() {
        return grp_karkarni_name;
    }

    public void setGrp_karkarni_name(String grp_karkarni_name) {
        this.grp_karkarni_name = grp_karkarni_name;
    }

    public String getGrp_karkarni_place() {
        return grp_karkarni_place;
    }

    public void setGrp_karkarni_place(String grp_karkarni_place) {
        this.grp_karkarni_place = grp_karkarni_place;
    }

    private String logo_id=null;
    private String grp_karkarni_imglink=null;
    private String grp_karkarni_name=null;
    private String grp_karkarni_place=null;

    @Override
    public int compare(KaryakarniList karyakarniList, KaryakarniList t1) {
        return 0;
    }
}
