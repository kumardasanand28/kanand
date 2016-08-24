package com.java.register.jpabean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users_address")
public class AddressJPABean {

	@Id 
	@Column(name = "ADDRESS_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long addressId;


	@Column(name="ADDRESS_NICK_NAME")
	private String addressNickName;

	@OneToOne(mappedBy = "address")
	private AddressDetailsJPA addressDetails;



	public long getAddressId() {
		return addressId;
	}



	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}


	public String getAddressNickName() {
		return addressNickName;
	}



	public void setAddressNickName(String addressNickName) {
		this.addressNickName = addressNickName;
	}

}
