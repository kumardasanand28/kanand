package com.java.register.jpabean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users_address")
public class AddressJPABean {

	@Id 
	@Column(name = "ADDRESS_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;


	@Column(name="ADDRESS_NICK_NAME")
	private String addressNickName;



	@ManyToOne
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private UserJPABean user; 

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



	public UserJPABean getUser() {
		return user;
	}



	public void setUser(UserJPABean user) {
		this.user = user;
	}


	
	public AddressDetailsJPA getAddressDetails() {
		return addressDetails;
	}



	public void setAddressDetails(AddressDetailsJPA addressDetails) {
		this.addressDetails = addressDetails;
	}

}
