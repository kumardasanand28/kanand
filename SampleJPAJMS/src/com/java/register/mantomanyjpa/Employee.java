package com.java.register.mantomanyjpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EMPLOYEE_ID")
	private Long id;
	
	@Column(name="EMPLOYEE_NAME")
	private String name;

	@OneToMany(mappedBy="employee")
	private List<EmpProjectAssociation> projects;

	public Employee(){
		
	}
	
	public Employee(String name){
		this.name = name;
	}
	
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

	public List<EmpProjectAssociation> getProjects() {
		return projects;
	}

	public void setProjects(List<EmpProjectAssociation> projects) {
		this.projects = projects;
	}

}
