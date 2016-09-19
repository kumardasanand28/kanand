package com.java.register.testjpa.immutable;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.annotation.concurrent.Immutable;

@Entity
@Immutable
@Table(name="POLICY_DETAILS")
public class IPolicy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="POLICY_ID")
	private long id;
	
	@Column(name="POLICY_NAME")
	private String name;
	
	@OneToMany(mappedBy="policy",targetEntity=IVehicle.class,fetch=FetchType.EAGER)
	private Collection<IVehicle> vehicles;
	
	public void removeVehicleChild(IVehicle vehicle){
		vehicles.remove(vehicle);
		if(vehicle != null){
			vehicle.setPolicy(null);
		}
	}
	
	public IPolicy(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPolicyName() {
		return name;
	}

	public void setPolicyName(String policyName) {
		this.name = policyName;
	}

	public Collection<IVehicle> getVehicleList() {
		return vehicles;
	}

	public void setVehicleList(Collection<IVehicle> vehicleList) {
		this.vehicles = vehicleList;
	}

}
