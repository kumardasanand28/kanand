package com.java.register.bean;

import java.util.List;
import java.util.Map;

public class User {

	
	private long id;
	
	private String fullName;
	
	private int age;
	
	private String qualification;
	
	private int yearPassed;
	
	private String gender;
	

	private List<Address> addressList;
	
	private String addressAsText;


	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public int getYearPassed() {
		return yearPassed;
	}

	public void setYearPassed(int yearPassed) {
		this.yearPassed = yearPassed;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public String getAddressAsText() {
		addressAsText = null;
		StringBuffer sbf = new StringBuffer();
		int count =1;
		for(Address addressBean : addressList){
			sbf.append("<p>");
			sbf.append("<b>Address "+count+":    </b>");
			sbf.append(addressBean.getAddressNickName());
			sbf.append(" ");
			sbf.append(addressBean.getStreet());
			sbf.append(" ");
			sbf.append( addressBean.getCity());
			sbf.append(" ");
			sbf.append(addressBean.getState());
			sbf.append(" ");
			sbf.append(addressBean.getZip());
			sbf.append("</p>");
			count++;
		}
		addressAsText = sbf.toString();
		return addressAsText;
	}

	public void setAddressAsText(String addressAsText) {
		this.addressAsText = addressAsText;
	}
	

}
