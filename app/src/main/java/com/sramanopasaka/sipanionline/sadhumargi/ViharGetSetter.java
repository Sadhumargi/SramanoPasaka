package com.sramanopasaka.sipanionline.sadhumargi;

import java.io.Serializable;

/**
 * Created by SipaniOnline on 11-07-2016.
 */
public class ViharGetSetter implements Serializable {

    static String GS_NO = "";
    static String GS_IMAGE_LINK = "";
    static String GS_NAME = "";
    static String GS_ASSIST_NAME = "";
    static String GS_GROUP_NO = "";

    static String GS_FROM_TO = "";
    static String GS_vihar_location = "";
    static String GS_ATTENDER_NAME = "";

    public static String getGsAttenderPhone() {
        return GS_ATTENDER_PHONE;
    }

    public static void setGsAttenderPhone(String gsAttenderPhone) {
        GS_ATTENDER_PHONE = gsAttenderPhone;
    }

    static String GS_ATTENDER_PHONE = "";
    static  double GS_MY_LAT;
    static  double GS_MY_LNG;
    static  double GS_DIS;
    static String GS_PHONE;


    public static double getGsDis() {
        return GS_DIS;
    }

    public static String getGsPhone() {
        return GS_PHONE;
    }

    public static void setGsPhone(String gsPhone) {
        GS_PHONE = gsPhone;
    }

    public static void setGsDis(double gsDis) {
        GS_DIS = gsDis;
    }

    public static double getGsMyLat() {
        return GS_MY_LAT;
    }

    public static void setGsMyLat(double gsMyLat) {
        GS_MY_LAT = gsMyLat;
    }

    public static double getGsMyLng() {
        return GS_MY_LNG;
    }

    public static void setGsMyLng(double gsMyLng) {
        GS_MY_LNG = gsMyLng;
    }

    static  double GS_LAT;
    static  double GS_LNG;


    public static double getGsLat() {
        return GS_LAT;
    }

    public static void setGsLat(double gsLat) {
        GS_LAT = gsLat;
    }

    public static double getGsLng() {
        return GS_LNG;
    }

    public static void setGsLng(double gsLng) {
        GS_LNG = gsLng;
    }

    public static String getGsNo() {
        return GS_NO;
    }

    public static void setGsNo(String gsNo) {
        GS_NO = gsNo;
    }

    public static String getGsImageLink() {
        return GS_IMAGE_LINK;
    }

    public static void setGsImageLink(String gsImageLink) {
        GS_IMAGE_LINK = gsImageLink;
    }

    public static String getGsName() {
        return GS_NAME;
    }

    public static void setGsName(String gsName) {
        GS_NAME = gsName;
    }

    public static String getGsAssistName() {
        return GS_ASSIST_NAME;
    }

    public static void setGsAssistName(String gsAssistName) {
        GS_ASSIST_NAME = gsAssistName;
    }

    public static String getGsGroupNo() {
        return GS_GROUP_NO;
    }

    public static void setGsGroupNo(String gsGroupNo) {
        GS_GROUP_NO = gsGroupNo;
    }

    public static String getGsFromTo() {
        return GS_FROM_TO;
    }

    public static void setGsFromTo(String gsFromTo) {
        GS_FROM_TO = gsFromTo;
    }

    public static String getGS_vihar_location() {
        return GS_vihar_location;
    }

    public static void setGS_vihar_location(String GS_vihar_location) {
        ViharGetSetter.GS_vihar_location = GS_vihar_location;
    }

    public static String getGsAttenderName() {
        return GS_ATTENDER_NAME;
    }

    public static void setGsAttenderName(String gsAttenderName) {
        GS_ATTENDER_NAME = gsAttenderName;
    }

    public static String getGsAttenderChiefName() {
        return GS_ATTENDER_CHIEF_NAME;
    }

    public static void setGsAttenderChiefName(String gsAttenderChiefName) {
        GS_ATTENDER_CHIEF_NAME = gsAttenderChiefName;
    }

    public static String getGsSection() {
        return GS_SECTION;
    }

    public static void setGsSection(String gsSection) {
        GS_SECTION = gsSection;
    }

    public static String getGsDate() {
        return GS_DATE;
    }

    public static void setGsDate(String gsDate) {
        GS_DATE = gsDate;
    }

    static String GS_ATTENDER_CHIEF_NAME = "";

    static String GS_SECTION = "";
    static String GS_DATE = "";


}
