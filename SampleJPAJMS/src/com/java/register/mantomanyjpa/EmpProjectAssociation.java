package com.java.register.mantomanyjpa;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EMP_PROJECTS")
public class EmpProjectAssociation {
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="EMPLOYEE_ID", referencedColumnName="EMPLOYEE_ID")
	private Employee employee;
	
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="PROJECT_ID", referencedColumnName="PROJECT_ID")
	private Project project;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="ACTIVE")
	private boolean isActive;


	public EmpProjectAssociation(){
		
	}
	
	public EmpProjectAssociation(Date date, boolean isActive){
		this.createdDate = date;
		this.isActive = isActive;
	}
	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
