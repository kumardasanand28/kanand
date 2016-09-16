package com.java.register.testjpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESS_DETAILS")
public class AddressJPABean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ADDRESS_ID")
	private long id;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="VEHICLE_ID", referencedColumnName="VEHICLE_ID")
	private VehicleJPABean vehicle;
	
	public AddressJPABean(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public VehicleJPABean getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleJPABean vehicle) {
		this.vehicle = vehicle;
	}
	

}
