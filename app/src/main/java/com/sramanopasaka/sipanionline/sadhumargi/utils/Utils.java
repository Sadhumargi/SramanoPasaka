package com.sramanopasaka.sipanionline.sadhumargi.utils;

/**
 * Name    :   pranavjdev
 * Date   : 9/1/17
 * Email : pranavjaydev@gmail.com
 */

public class Utils {

    private static long HALF_DAY = 12 * 60 * 60 * 1000;

    public static boolean needToCallApi(long lastUpated, long currentTime) {
        if (lastUpated == 0)
            return true;
        long twelveHourBefore = currentTime - HALF_DAY;
        if (lastUpated > twelveHourBefore)
            return false;

        return true;
    }
}
