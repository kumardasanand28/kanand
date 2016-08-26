package com.java.register.bean;

import java.util.List;
import java.util.Map;

import com.java.register.constants.UserConstants;

public class User {

	
	private long id;
	
	private String fullName;
	
	private String age;
	
	private String qualification;
	
	private String yearPassed;
	
	private String gender;
	
	private List<String> projectName;
	

	private List<Address> addressList;
	
	private String addressAsText;


	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return Integer.parseInt(age);
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public int getYearPassed() {
		return Integer.parseInt(yearPassed);
	}

	public void setYearPassed(String yearPassed) {
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

	public List<String> getProjectName() {
		return projectName;
	}

	public void setProjectName(List<String> projectName) {
		this.projectName = projectName;
	}
	

}
