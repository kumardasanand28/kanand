package com.java.register.testjpa;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VEHICLE_DETAILS")
public class VehicleJPABean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VEHICLE_ID")
	private long id;
	
	@Column(name="VEHICLE_NAME")
	private String vehicleName;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="POLICY_ID", referencedColumnName="POLICY_ID")
	private PolicyJPABean policy;
	
	
	@OneToMany(mappedBy="vehicle", cascade = CascadeType.ALL, targetEntity=AddressJPABean.class)
	private Collection<AddressJPABean> addressList;
	
	
	public VehicleJPABean(){
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getVehicleName() {
		return vehicleName;
	}


	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}


	public PolicyJPABean getPolicy() {
		return policy;
	}


	public void setPolicy(PolicyJPABean policy) {
		this.policy = policy;
	}


	public Collection<AddressJPABean> getAddressList() {
		return addressList;
	}


	public void setAddressList(Collection<AddressJPABean> addressList) {
		this.addressList = addressList;
	}


}
