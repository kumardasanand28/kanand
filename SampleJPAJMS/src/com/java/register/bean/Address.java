package com.java.register.bean;

public class Address {


	private String addressNickName;


	private long addressId;
	
	private long addressDetailsid;

	private String street;

	private String city;

	private String state;

	private String zip;

	public String getAddressNickName() {
		return addressNickName;
	}

	public void setAddressNickName(String addressNickName) {
		this.addressNickName = addressNickName;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getAddressDetailsid() {
		return addressDetailsid;
	}

	public void setAddressDetailsid(long addressDetailsid) {
		this.addressDetailsid = addressDetailsid;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
