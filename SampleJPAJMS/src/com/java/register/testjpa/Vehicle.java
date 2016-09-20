package com.java.register.testjpa;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VEHICLE_DETAILS")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VEHICLE_ID")
	private Long id;
	
	@Column(name="VEHICLE_NAME")
	private String vehicleName;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="POLICY_ID", referencedColumnName="POLICY_ID")
	private Policy policy;
	
	
	@OneToMany(mappedBy="vehicle",cascade=CascadeType.ALL,
			targetEntity=Address.class,fetch=FetchType.EAGER)
	private Collection<Address> address;
	
	
	public Vehicle(){
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getVehicleName() {
		return vehicleName;
	}


	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}


	public Policy getPolicy() {
		return policy;
	}


	public void setPolicy(Policy policy) {
		this.policy = policy;
	}


	public Collection<Address> getAddressList() {
		return address;
	}


	public void setAddressList(Collection<Address> addressList) {
		this.address = addressList;
	}


}
