package com.sramanopasaka.sipanionline.sadhumargi.model;

public class State{
	private String area;
	private String country;
	private String capital;
	private String name;
	private String largestCity;
	private String abbr;

	public void setArea(String area){
		this.area = area;
	}

	public String getArea(){
		return area;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCapital(String capital){
		this.capital = capital;
	}

	public String getCapital(){
		return capital;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLargestCity(String largestCity){
		this.largestCity = largestCity;
	}

	public String getLargestCity(){
		return largestCity;
	}

	public void setAbbr(String abbr){
		this.abbr = abbr;
	}

	public String getAbbr(){
		return abbr;
	}

	@Override
 	public String toString(){
		return 
			"State{" + 
			"area = '" + area + '\'' + 
			",country = '" + country + '\'' + 
			",capital = '" + capital + '\'' + 
			",name = '" + name + '\'' + 
			",largest_city = '" + largestCity + '\'' + 
			",abbr = '" + abbr + '\'' + 
			"}";
		}
}
