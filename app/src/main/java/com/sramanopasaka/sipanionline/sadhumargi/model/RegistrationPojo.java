package com.sramanopasaka.sipanionline.sadhumargi.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Name    :   pranavjdev
 * Date   : 8/29/17
 * Email : pranavjaydev@gmail.com
 */

public class RegistrationPojo  implements Parcelable {

    public static final Creator<RegistrationPojo> CREATOR = new Creator<RegistrationPojo>() {
        @Override
        public RegistrationPojo createFromParcel(Parcel in) {
            return new RegistrationPojo(in);
        }

        @Override
        public RegistrationPojo[] newArray(int size) {
            return new RegistrationPojo[size];
        }
    };

    private String anchalId = null;

    public String getAnchalId() {
        return anchalId;
    }

    public void setAnchalId(String anchalId) {
        this.anchalId = anchalId;
    }

    public String getLocalSanghId() {
        return localSanghId;
    }

    public void setLocalSanghId(String localSanghId) {
        this.localSanghId = localSanghId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getSaluation() {
        return saluation;
    }

    public void setSaluation(String saluation) {
        this.saluation = saluation;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    private String localSanghId = null;
    private String firstName = null;
    private String lastName = null;
    private String familyId;
    private String relationId = null;
    private String saluation = null;

    public RegistrationPojo(String anchalId, String localSanghId, String firstName, String lastName, String saluation, String city, String district) {
        this.anchalId = anchalId;
        this.localSanghId = localSanghId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.saluation = saluation;
        this.city = city;
        this.district = district;
    }

    private String post = null;
    private String city = null;
    private String district = null;
    private String state = null;
    private String country = null;
    private String mobile = null;
    private String birthDay = null;
    private String age = null;
    private String gender = null;
    private String emailAddress = null;
    private String profile_created_by=null;
    private String _refcode=null;
    private String _reg_type=null;

    public String getProfile_created_by() {
        return profile_created_by;
    }

    public void setProfile_created_by(String profile_created_by) {
        this.profile_created_by = profile_created_by;
    }

    public String get_refcode() {
        return _refcode;
    }

    public void set_refcode(String _refcode) {
        this._refcode = _refcode;
    }

    public String get_reg_type() {
        return _reg_type;
    }

    public void set_reg_type(String _reg_type) {
        this._reg_type = _reg_type;
    }

    protected RegistrationPojo(Parcel in) {
        anchalId = in.readString();
        localSanghId = in.readString();
        familyId = in.readString();
        relationId = in.readString();
        saluation = in.readString();
        post = in.readString();
        city = in.readString();
        district = in.readString();
        state = in.readString();
        country = in.readString();
        mobile = in.readString();
        birthDay = in.readString();
        age = in.readString();
        gender = in.readString();
        emailAddress = in.readString();
        firstName = in.readString();
        lastName = in.readString();

    }

    public static Creator<RegistrationPojo> getCREATOR() {
        return CREATOR;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(anchalId);
        parcel.writeString(localSanghId);
        parcel.writeString(familyId);
        parcel.writeString(relationId);
        parcel.writeString(saluation);
        parcel.writeString(post);
        parcel.writeString(city);
        parcel.writeString(district);
        parcel.writeString(state);
        parcel.writeString(country);
        parcel.writeString(mobile);
        parcel.writeString(birthDay);
        parcel.writeString(age);
        parcel.writeString(gender);
        parcel.writeString(emailAddress);
        parcel.writeString(firstName);
        parcel.writeString(lastName);


    }

    
}
