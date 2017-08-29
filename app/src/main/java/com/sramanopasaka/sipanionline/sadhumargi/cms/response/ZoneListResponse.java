package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.Zone;

import java.util.List;

/**
 * Created by sipani001 on 28/8/17.
 */

public class ZoneListResponse  extends GUIResponse {

    private boolean statusCode=false;
    private List<Zone> zoneList;

    public void setStatusCode(boolean statusCode) {
        this.statusCode = statusCode;
    }

    public List<Zone> getZoneList() {
        return zoneList;
    }

    public void setZoneList(List<Zone> zoneList) {
        this.zoneList = zoneList;
    }

    public boolean isStatusCode() {
        return statusCode;
    }

   

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean status) {

    }
}
