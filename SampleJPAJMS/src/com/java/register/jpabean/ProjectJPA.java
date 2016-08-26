package com.java.register.jpabean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_MAPPED_PROJECT")
public class ProjectJPA {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private int id;


	@Column(name = "PROJECT_NAME")
	private String projectName;


	@OneToOne(cascade=CascadeType.ALL)  
	@JoinTable(name = "USER_PROJECT", 
	joinColumns = @JoinColumn(name = "PROJECT_ID",referencedColumnName="PROJECT_ID"),
	inverseJoinColumns = @JoinColumn(name = "USER_ID",referencedColumnName="USER_ID"))
	private UserJPABean users;



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public UserJPABean getUsers() {
		return users;
	}


	public void setUsers(UserJPABean users) {
		this.users = users;
	}

}
