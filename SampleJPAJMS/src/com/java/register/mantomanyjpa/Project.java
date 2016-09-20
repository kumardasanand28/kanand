package com.java.register.mantomanyjpa;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PROJECTS")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PROJECT_ID")
	private long id;
	
	@Column(name="PROJECT_NAME")
	private String name;
	
	@OneToMany(mappedBy="project")
	private List<EmpProjectAssociation> employees;
	
	public Project(){
		
	}
	public EmpProjectAssociation addEmployee(Employee employee){
		EmpProjectAssociation association = new EmpProjectAssociation();
		association.setEmployee(employee);
		association.setProject(this);
		association.setActive(true);
		java.util.Date date = new java.util.Date();
		association.setCreatedDate(new Date(date.getTime()));
		return association;
	}
	public Project(String name){
		this.name = name;
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

	public List<EmpProjectAssociation> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmpProjectAssociation> employees) {
		this.employees = employees;
	}


}
