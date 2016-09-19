package com.java.register.testjpa;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="POLICY_DETAILS")
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="POLICY_ID")
	private Long id;
	
	@Column(name="POLICY_NAME")
	private String name;
	
	
	@OneToMany(mappedBy="policy", cascade={CascadeType.PERSIST, CascadeType.REMOVE},
			targetEntity=Party.class)
	private Collection<Party> parties;
	
	@OneToMany(mappedBy="policy",cascade={CascadeType.PERSIST, CascadeType.REMOVE}, 
			targetEntity=Vehicle.class)
	private Collection<Vehicle> vehicles;
	
	public void removePartyChild(Party party){
		parties.remove(party);
		if(party != null){
			party.setPolicy(null);
		}
	}

	
	public Policy(){
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPolicyName() {
		return name;
	}


	public void setPolicyName(String policyName) {
		this.name = policyName;
	}


/*	public Collection<VehicleJPABean> getVehicleList() {
		return vehicleList;
	}


	public void setVehicleList(Collection<VehicleJPABean> vehicleList) {
		this.vehicleList = vehicleList;
	}*/


	public Collection<Party> getPartyList() {
		return parties;
	}


	public void setPartyList(Collection<Party> partyList) {
		this.parties = partyList;
	}


	public Collection<Vehicle> getVehicleList() {
		return vehicles;
	}


	public void setVehicleList(Collection<Vehicle> vehicleList) {
		this.vehicles = vehicleList;
	}
}
