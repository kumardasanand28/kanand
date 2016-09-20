package com.java.register.testjpa.manytomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="PARTY")
public class MParty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PARTY_ID")
	private Long id;

	@Column(name="PARTY_NAME")
	private String name;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "POLICY_PARTY",
	joinColumns=@JoinColumn(name="PARTY_ID",referencedColumnName="PARTY_ID"),
	inverseJoinColumns=@JoinColumn(name="POLICY_ID",referencedColumnName="POLICY_ID"))
	private List<MPolicy> policies = new ArrayList<MPolicy>();

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

	public List<MPolicy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<MPolicy> policies) {
		this.policies = policies;
	}

}
