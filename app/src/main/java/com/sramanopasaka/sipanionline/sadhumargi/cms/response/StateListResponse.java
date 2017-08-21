package com.sramanopasaka.sipanionline.sadhumargi.cms.response;

import com.sramanopasaka.sipanionline.sadhumargi.model.Achievements;
import com.sramanopasaka.sipanionline.sadhumargi.model.Country;
import com.sramanopasaka.sipanionline.sadhumargi.model.State;

import java.util.List;

/**
 * Created by sipani001 on 17/8/17.
 */

public class StateListResponse extends GUIResponse{

    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }

    private List<State> stateList = null;

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean status) {

    }
}
