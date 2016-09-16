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
@Table(name="PARTY_DETAILS")
public class PartyJPABean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PARTY_ID")
	private long id;
	
	@Column(name="PARTY_NAME")
	private String partyName;
	
	/*@OneToMany(mappedBy="party", cascade = CascadeType.ALL, targetEntity=PolicyJPABean.class)
	private Collection<PolicyJPABean> policyList;*/
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="POLICY_ID", referencedColumnName="POLICY_ID")
	private PolicyJPABean policy;
	
	public PartyJPABean(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

}
