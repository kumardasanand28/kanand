package com.java.register.jpabean;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users_address_details")
public class AddressDetailsJPA {


	@Id 
	@Column(name = "ADDRESS_DETAILS_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long addressDetailsid;

	@Column(name="STREET_NAME")
	private String street;

	@Column(name="CITY")
	private String city;

	@Column(name="STATE")
	private String state;

	@Column(name="ZIP_CODE")
	private String zip;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	private AddressJPABean address;

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


	public AddressJPABean getAddress() {
		return address;
	}


	public void setAddress(AddressJPABean address) {
		this.address = address;
	}
}
