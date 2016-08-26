package com.java.register.jpabean;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="USERS")
public class UserJPABean {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private long id;

	@Column(name="FULL_NAME")
	@NotNull
	@Size(max=30,min=3,message="Invalid Name")
	private String fullName;

	@Column(name="AGE")
	@NotNull
	@Digits(integer=3, fraction = 0,message="Invalid Age")
	private int age;

	@Column(name="GENDER")
	@NotNull
	private String gender;

	@NotNull
	@Digits(integer=4, fraction = 0,message="Invalid Year")
	@Column(name="PASSED_YEAR")
	private int passedYear;
	
	@NotNull
	@Column(name="QUALIFICATION")
	private String qualification;
	
	
	public UserJPABean(String name, int age,String gender,int passedYear,String qualification){
		this.fullName = name;
		this.age = age;
		this.gender = gender;
		this.passedYear = passedYear;
		this.qualification = qualification;
	}
	
	public UserJPABean(){
		
	}
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "USER_PROJECT", 
	joinColumns = @JoinColumn(name = "USER_ID",referencedColumnName="USER_ID"),
	inverseJoinColumns = @JoinColumn(name = "PROJECT_ID",referencedColumnName="PROJECT_ID"))
	private Set<ProjectJPA> project = new HashSet<ProjectJPA>();
	
	
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, targetEntity=AddressJPABean.class)
	private Collection<AddressJPABean> addressList;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Collection<AddressJPABean> getAddressList() {
		return addressList;
	}

	public void setAddressList(Collection<AddressJPABean> addressList) {
		this.addressList = addressList;
	}

	public int getPassedYear() {
		return passedYear;
	}

	public void setPassedYear(int passedYear) {
		this.passedYear = passedYear;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Set<ProjectJPA> getProject() {
		return project;
	}

	public void setProject(Set<ProjectJPA> project) {
		this.project = project;
	}

}
