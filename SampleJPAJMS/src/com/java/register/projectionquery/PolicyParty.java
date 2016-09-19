package com.java.register.projectionquery;

public class PolicyParty {
	
	private String policyName;
	
	private String partyName;
	
	
	public PolicyParty(String policyName, String partyName){
		this.policyName = policyName;
		this.partyName = partyName;
	}
	
	public PolicyParty(){
		
	}


	public String getPolicyName() {
		return policyName;
	}


	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}


	public String getPartyName() {
		return partyName;
	}


	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
}
