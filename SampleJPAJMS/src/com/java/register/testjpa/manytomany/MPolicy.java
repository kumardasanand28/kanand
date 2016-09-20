package com.java.register.testjpa.manytomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="POLICY")
public class MPolicy {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="POLICY_ID")
	private Long id;
	
	@Column(name="POLICY_NAME")
	private String name;
	
	@ManyToMany(mappedBy="policies",cascade=CascadeType.ALL)
	private List<MParty> parties  = new ArrayList<MParty>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MParty> getParties() {
		return parties;
	}

	public void setParties(List<MParty> parties) {
		this.parties = parties;
	}

}
