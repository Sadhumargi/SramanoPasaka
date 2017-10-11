package com.sramanopasaka.sipanionline.sadhumargi.listener;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;

/**
 * Name    :   pranavjdev
 * Date   : 8/9/17
 * Email : pranavjaydev@gmail.com
 */

public interface GUICallback {

    void onRequestProcessed(GUIResponse guiResponse,RequestStatus  requestStatus);

    public static enum RequestStatus {SUCCESS, FAILED}
}
