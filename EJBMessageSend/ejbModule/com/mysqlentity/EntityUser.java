package com.mysqlentity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TEST_USERS")
public class EntityUser {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name="FULL_NAME")
	private String name;

	@Column(name="EMP_ID")
	private String empId;
	
	
	public EntityUser(){

	}

	public EntityUser(String name, String empId){

		System.out.println(name +" "+empId);
		this.name = name;
		this.empId = empId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
}
