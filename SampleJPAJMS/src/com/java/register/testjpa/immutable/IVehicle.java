package com.java.register.testjpa.immutable;

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

import javax.annotation.concurrent.Immutable;

@Entity
@Immutable
@Table(name="VEHICLE_DETAILS")
public class IVehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VEHICLE_ID")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="POLICY_ID", referencedColumnName="POLICY_ID")
	private IPolicy policy;
	
	@OneToMany(mappedBy="vehicle", cascade={CascadeType.PERSIST}, targetEntity=IAddress.class,fetch=FetchType.EAGER)
	private Collection<IAddress> address;
	
	public IVehicle(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IPolicy getPolicy() {
		return policy;
	}

	public void setPolicy(IPolicy policy) {
		this.policy = policy;
	}

	public Collection<IAddress> getAddressList() {
		return address;
	}

	public void setAddressList(Collection<IAddress> addressList) {
		this.address = addressList;
	}

	

}
