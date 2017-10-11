package com.sramanopasaka.sipanionline.sadhumargi.cms.request;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Name    :   pranavjdev
 * Date   : 8/9/17
 * Email : pranavjaydev@gmail.com
 */

public class RegisterRequest extends GUIRequest {

    private String salution = null;

    private String first_name = null;

    private String middle_name = null;

    private String last_name = null;

    private String guardian_type = null;

    private String guardian_name = null;

    private String mother_name = null;

    private String address = null;

    private String city = null;

    private String state = null;

    private String district_n = null;

    private String pincode = null;

    private String country = null;

    private String country_code = null;

    private String mobile = null;

    private String whatsapp_number = null;

    private String alternate_number = null;

    private String child_count = null;

    private String gender = null;

    private String blood_group = null;

    private String birth_day = null;

    private String marital_status = null;

    private String marriage_date = null;

    private String email_address = null;

    private String is_head_of_family = null;

    private String password = null;

    public String getSalution() {
        return salution;
    }

    public void setSalution(String salution) {
        this.salution = salution;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGuardian_type() {
        return guardian_type;
    }

    public void setGuardian_type(String guardian_type) {
        this.guardian_type = guardian_type;
    }

    public String getGuardian_name() {
        return guardian_name;
    }

    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict_n() {
        return district_n;
    }

    public void setDistrict_n(String district_n) {
        this.district_n = district_n;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWhatsapp_number() {
        return whatsapp_number;
    }

    public void setWhatsapp_number(String whatsapp_number) {
        this.whatsapp_number = whatsapp_number;
    }

    public String getAlternate_number() {
        return alternate_number;
    }

    public void setAlternate_number(String alternate_number) {
        this.alternate_number = alternate_number;
    }

    public String getChild_count() {
        return child_count;
    }

    public void setChild_count(String child_count) {
        this.child_count = child_count;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getMarriage_date() {
        return marriage_date;
    }

    public void setMarriage_date(String marriage_date) {
        this.marriage_date = marriage_date;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getIs_head_of_family() {
        return is_head_of_family;
    }

    public void setIs_head_of_family(String is_head_of_family) {
        this.is_head_of_family = is_head_of_family;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public JSONObject getURLEncodedPostdata() {
        JSONObject object = new JSONObject();
        try {
            object.put("salution", salution);
            object.put("first_name", first_name);
            object.put("middle_name", middle_name);
            object.put("last_name", last_name);
            object.put("guardian_type", guardian_type);
            object.put("guardian_name", guardian_name);
            object.put("mother_name", mother_name);
            object.put("address", address);
            object.put("city", city);
            object.put("state", state);
            object.put("district_n", district_n);
            object.put("pincode", pincode);
            object.put("country", country);
            object.put("country_code", country_code);
            object.put("mobile", mobile);
            object.put("whatsapp_number", whatsapp_number);
            object.put("alternate_number", alternate_number);
            object.put("child_count", child_count);
            object.put("gender", gender);
            object.put("blood_group", blood_group);
            object.put("birth_day", birth_day);
            object.put("marital_status", marital_status);
            object.put("marriage_date", marriage_date);
            object.put("email_address", email_address);
            object.put("is_head_of_family", is_head_of_family);
            object.put("password", password);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
