package com.sramanopasaka.sipanionline.sadhumargi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Name    :   pranavjdev
 * Date   : 9/4/17
 * Email : pranavjaydev@gmail.com
 */

public class DateTimeUtils {

    public static long dateToMillisecs(String time){
        try {
            DateFormat ServerFormat = new SimpleDateFormat("dd MM yyyy");
            Date date = ServerFormat.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
