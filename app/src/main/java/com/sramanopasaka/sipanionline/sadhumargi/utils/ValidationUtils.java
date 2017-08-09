package com.sramanopasaka.sipanionline.sadhumargi.utils;

import android.util.Patterns;

/**
 * Name    :   pranavjdev
 * Date   : 8/9/17
 * Email : pranavjaydev@gmail.com
 */

public class ValidationUtils {

    public static boolean isValidMail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //Validates
    public static boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
}
