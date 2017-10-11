package com.sramanopasaka.sipanionline.sadhumargi.model;

import com.google.gson.annotations.SerializedName;

public class BasicDetailsData{

	@SerializedName("country")
	private String country;

	@SerializedName("guardian_name")
	private String guardianName;

	@SerializedName("gender")
	private String gender;

	@SerializedName("city")
	private String city;

	@SerializedName("mother_name")
	private String motherName;

	@SerializedName("district_n")
	private String districtN;

	@SerializedName("guardian_type")
	private String guardianType;

	@SerializedName("id")
	private String id;

	@SerializedName("state")
	private String state;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("is_head_of_family")
	private String isHeadOfFamily;

	@SerializedName("whatsapp_number")
	private String whatsappNumber;

	@SerializedName("pincode")
	private String pincode;

	@SerializedName("address")
	private String address;

	@SerializedName("child_count")
	private String childCount;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("profile_pic")
	private String profilePic;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("middle_name")
	private String middleName;

	@SerializedName("marriage_date")
	private String marriageDate;

	@SerializedName("birth_day")
	private String birthDay;

	@SerializedName("country_code")
	private String countryCode;

	@SerializedName("marital_status")
	private String maritalStatus;

	@SerializedName("alternate_number")
	private String alternateNumber;

	@SerializedName("email_address")
	private String emailAddress;

	@SerializedName("blood_group")
	private String bloodGroup;

	@SerializedName("salution")
	private String salution;

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

	public void setMotherName(String motherName){
		this.motherName = motherName;
	}

	public String getMotherName(){
		return motherName;
	}

	public void setDistrictN(String districtN){
		this.districtN = districtN;
	}

	public String getDistrictN(){
		return districtN;
	}

	public void setGuardianType(String guardianType){
		this.guardianType = guardianType;
	}

	public String getGuardianType(){
		return guardianType;
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

	public void setWhatsappNumber(String whatsappNumber){
		this.whatsappNumber = whatsappNumber;
	}

	public String getWhatsappNumber(){
		return whatsappNumber;
	}

	public void setPincode(String pincode){
		this.pincode = pincode;
	}

	public String getPincode(){
		return pincode;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setChildCount(String childCount){
		this.childCount = childCount;
	}

	public String getChildCount(){
		return childCount;
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

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setMaritalStatus(String maritalStatus){
		this.maritalStatus = maritalStatus;
	}

	public String getMaritalStatus(){
		return maritalStatus;
	}

	public void setAlternateNumber(String alternateNumber){
		this.alternateNumber = alternateNumber;
	}

	public String getAlternateNumber(){
		return alternateNumber;
	}

	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress(){
		return emailAddress;
	}

	public void setBloodGroup(String bloodGroup){
		this.bloodGroup = bloodGroup;
	}

	public String getBloodGroup(){
		return bloodGroup;
	}

	public void setSalution(String salution){
		this.salution = salution;
	}

	public String getSalution(){
		return salution;
	}

	@Override
 	public String toString(){
		return 
			"BasicDetailsData{" + 
			"country = '" + country + '\'' + 
			",guardian_name = '" + guardianName + '\'' + 
			",gender = '" + gender + '\'' + 
			",city = '" + city + '\'' + 
			",mother_name = '" + motherName + '\'' + 
			",district_n = '" + districtN + '\'' + 
			",guardian_type = '" + guardianType + '\'' + 
			",id = '" + id + '\'' + 
			",state = '" + state + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",is_head_of_family = '" + isHeadOfFamily + '\'' + 
			",whatsapp_number = '" + whatsappNumber + '\'' + 
			",pincode = '" + pincode + '\'' + 
			",address = '" + address + '\'' + 
			",child_count = '" + childCount + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",profile_pic = '" + profilePic + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",middle_name = '" + middleName + '\'' + 
			",marriage_date = '" + marriageDate + '\'' + 
			",birth_day = '" + birthDay + '\'' + 
			",country_code = '" + countryCode + '\'' + 
			",marital_status = '" + maritalStatus + '\'' + 
			",alternate_number = '" + alternateNumber + '\'' + 
			",email_address = '" + emailAddress + '\'' + 
			",blood_group = '" + bloodGroup + '\'' + 
			",salution = '" + salution + '\'' + 
			"}";
		}
}