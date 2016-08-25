package com.java.register.jpabean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class UserJPABean {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private long id;

	@Column(name="FULL_NAME")
	private String fullName;

	@Column(name="AGE")
	private int age;

	@Column(name="GENDER")
	private String gender;


	@Column(name="PASSED_YEAR")
	private int passedYear;
	
	@Column(name="QUALIFICATION")
	private String qualification;
	
	
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, targetEntity=AddressJPABean.class)
	private Collection<AddressJPABean> addressList;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Collection<AddressJPABean> getAddressList() {
		return addressList;
	}

	public void setAddressList(Collection<AddressJPABean> addressList) {
		this.addressList = addressList;
	}

	public int getPassedYear() {
		return passedYear;
	}

	public void setPassedYear(int passedYear) {
		this.passedYear = passedYear;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

}
