package com.sramanopasaka.sipanionline.sadhumargi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel{
	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("message")
	@Expose
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@SerializedName("first_name")
	@Expose
	private String firstName;
	@SerializedName("middle_name")
	@Expose
	private String middleName;
	@SerializedName("last_name")
	@Expose
	private String lastName;
	@SerializedName("guardian_type")
	@Expose
	private String guardianType;
	@SerializedName("guardian_name")
	@Expose
	private String guardianName;
	@SerializedName("mother_name")
	@Expose
	private String motherName;
	@SerializedName("address")
	@Expose
	private String address;
	@SerializedName("city")
	@Expose
	private String city;
	@SerializedName("state")
	@Expose
	private String state;
	@SerializedName("district")
	@Expose
	private Object district;
	@SerializedName("district_n")
	@Expose
	private Object districtN;
	@SerializedName("country")
	@Expose
	private String country;
	@SerializedName("mobile")
	@Expose
	private String mobile;
	@SerializedName("gender")
	@Expose
	private String gender;
	@SerializedName("birth_day")
	@Expose
	private String birthDay;
	@SerializedName("blood_group")
	@Expose
	private String bloodGroup;
	@SerializedName("marital_status")
	@Expose
	private String maritalStatus;
	@SerializedName("marriage_date")
	@Expose
	private String marriageDate;
	@SerializedName("profile_pic")
	@Expose
	private String profilePic;
	@SerializedName("email_address")
	@Expose
	private String emailAddress;
	@SerializedName("is_head_of_family")
	@Expose
	private String isHeadOfFamily;
	@SerializedName("password")
	@Expose
	private String password;
	@SerializedName("app_token")
	@Expose
	private String appToken;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setGuardianName(String guardianName){
		this.guardianName = guardianName;
	}

	public String getGuardianName(){
		return guardianName;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setAppToken(String appToken){
		this.appToken = appToken;
	}

	public String getAppToken(){
		return appToken;
	}

	public void setMotherName(String motherName){
		this.motherName = motherName;
	}

	public String getMotherName(){
		return motherName;
	}

	public void setDistrictN(String districtN){
		this.districtN = districtN;
	}

	public Object getDistrictN(){
		return districtN;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setProfilePic(String profilePic){
		this.profilePic = profilePic;
	}

	public String getProfilePic(){
		return profilePic;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setMiddleName(String middleName){
		this.middleName = middleName;
	}

	public String getMiddleName(){
		return middleName;
	}

	public void setMarriageDate(String marriageDate){
		this.marriageDate = marriageDate;
	}

	public String getMarriageDate(){
		return marriageDate;
	}

	public void setBirthDay(String birthDay){
		this.birthDay = birthDay;
	}

	public String getBirthDay(){
		return birthDay;
	}

	public void setMaritalStatus(String maritalStatus){
		this.maritalStatus = maritalStatus;
	}

	public String getMaritalStatus(){
		return maritalStatus;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress(){
		return emailAddress;
	}

	public void setGuardianType(String guardianType){
		this.guardianType = guardianType;
	}

	public String getGuardianType(){
		return guardianType;
	}

	public void setDistrict(String district){
		this.district = district;
	}

	public Object getDistrict(){
		return district;
	}

	public void setBloodGroup(String bloodGroup){
		this.bloodGroup = bloodGroup;
	}

	public String getBloodGroup(){
		return bloodGroup;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setIsHeadOfFamily(String isHeadOfFamily){
		this.isHeadOfFamily = isHeadOfFamily;
	}

	public String getIsHeadOfFamily(){
		return isHeadOfFamily;
	}

	@Override
 	public String toString(){
		return 
			"LoginModel{" + 
			"country = '" + country + '\'' + 
			",guardian_name = '" + guardianName + '\'' + 
			",address = '" + address + '\'' + 
			",gender = '" + gender + '\'' + 
			",city = '" + city + '\'' + 
			",app_token = '" + appToken + '\'' + 
			",mother_name = '" + motherName + '\'' + 
			",district_n = '" + districtN + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",profile_pic = '" + profilePic + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",middle_name = '" + middleName + '\'' + 
			",marriage_date = '" + marriageDate + '\'' + 
			",birth_day = '" + birthDay + '\'' + 
			",marital_status = '" + maritalStatus + '\'' + 
			",password = '" + password + '\'' + 
			",email_address = '" + emailAddress + '\'' + 
			",guardian_type = '" + guardianType + '\'' + 
			",district = '" + district + '\'' + 
			",blood_group = '" + bloodGroup + '\'' + 
			",id = '" + id + '\'' + 
			",state = '" + state + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",is_head_of_family = '" + isHeadOfFamily + '\'' + 
			"}";
		}
}
