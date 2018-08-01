package com.sramanopasaka.sipanionline.sadhumargi.model;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;

import java.util.ArrayList;

public class CalenderModel extends GUIResponse {

    private  String Date = null;
    private  String Sunrise = null;
    private  String Sunset = null;
    private  String Navkarsi = null;
    private  String Porsi = null;
    private  String Sadhporsi = null;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getSunrise() {
        return Sunrise;
    }

    public void setSunrise(String sunrise) {
        this.Sunrise = sunrise;
    }

    public String getSunset() {
        return Sunset;
    }

    public void setSunset(String sunset) {
        this.Sunset = sunset;
    }

    public String getNavkarsi() {
        return Navkarsi;
    }

    public void setNavkarsi(String navkarsi) {
        this.Navkarsi = navkarsi;
    }

    public String getPorsi() {
        return Porsi;
    }

    public void setPorsi(String porsi) {
        this.Porsi = porsi;
    }

    public String getSadhporsi() {
        return Sadhporsi;
    }

    public void setSadhporsi(String sadhporsi) {
        this.Sadhporsi = sadhporsi;
    }

    public String getPurimaddha() {
        return Purimaddha;
    }

    public void setPurimaddha(String purimaddha) {
        this.Purimaddha = purimaddha;
    }

    public String getAvadhha() {
        return Avadhha;
    }

    public void setAvadhha(String avadhha) {
        this.Avadhha = avadhha;
    }

    public ArrayList<String> getUdveg() {
        return Udveg;
    }

    public void setUdveg(ArrayList<String> udveg) {
        this.Udveg = udveg;
    }

    public ArrayList<String> getChal() {
        return Chal;
    }

    public void setChal(ArrayList<String> chal) {
        this.Chal = chal;
    }

    public ArrayList<String> getLabh() {
        return Labh;
    }

    public void setLabh(ArrayList<String> labh) {
        this.Labh = labh;
    }

    public ArrayList<String> getAmrit() {
        return Amrit;
    }

    public void setAmrit(ArrayList<String> amrit) {
        this.Amrit = amrit;
    }

    public ArrayList<String> getKaal() {
        return Kaal;
    }

    public void setKaal(ArrayList<String> kaal) {
        this.Kaal = kaal;
    }

    public ArrayList<String> getShubh() {
        return Shubh;
    }

    public void setShubh(ArrayList<String> shubh) {
        this.Shubh = shubh;
    }

    public ArrayList<String> getRog() {
        return Rog;
    }

    public void setRog(ArrayList<String> rog) {
        this.Rog = rog;
    }

    private  String Purimaddha = null;
    private  String Avadhha = null;

    ArrayList< String > Udveg = new ArrayList < String > ();
    ArrayList < String > Chal = new ArrayList < String > ();
    ArrayList < String > Labh = new ArrayList < String > ();
    ArrayList < String > Amrit = new ArrayList < String > ();
    ArrayList < String > Kaal = new ArrayList < String > ();
    ArrayList < String > Shubh = new ArrayList < String > ();
    ArrayList < String > Rog = new ArrayList < String > ();

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean status) {

    }
}

