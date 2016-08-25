package com.java.register.jpabean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER_MAPPED_PROJECT")
public class ProjectJPA {
	
	
	@Id
	@Column(name = "PROJECT_ID")
	private int id;
	
	
	@Column(name = "PROJECT_NAME")
	private String projectName;

	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "USER_PROJECT", 
	joinColumns = @JoinColumn(name = "PROJECT_ID"),
	inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	private Set<UserJPABean> users = new HashSet<UserJPABean>();
	
	

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


	public Set<UserJPABean> getUsers() {
		return users;
	}


	public void setUsers(Set<UserJPABean> users) {
		this.users = users;
	}

}
