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
@Table(name="POLICY_DETAILS")
public class PolicyJPABean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="POLICY_ID")
	private long id;
	
	@Column(name="POLICY_NAME")
	private String policyName;
	
	
	@OneToMany(mappedBy="policy", cascade = CascadeType.ALL, targetEntity=PartyJPABean.class)
	private Collection<PartyJPABean> partyList;
	
	@OneToMany(mappedBy="policy", cascade = CascadeType.ALL, targetEntity=VehicleJPABean.class)
	private Collection<VehicleJPABean> vehicleList;

	
	public PolicyJPABean(){
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getPolicyName() {
		return policyName;
	}


	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}


	public Collection<VehicleJPABean> getVehicleList() {
		return vehicleList;
	}


	public void setVehicleList(Collection<VehicleJPABean> vehicleList) {
		this.vehicleList = vehicleList;
	}


	public Collection<PartyJPABean> getPartyList() {
		return partyList;
	}


	public void setPartyList(Collection<PartyJPABean> partyList) {
		this.partyList = partyList;
	}
}
