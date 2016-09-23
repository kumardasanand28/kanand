package com.student.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class UserEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="FULL_NAME")
	private String studentName;

	@Column(name="STUDENT_HOBBY")
	private String studentHobby;

	@Column(name="STUDENT_MOBILE_NUMBER")
	private String studentMobileNo;

	@Column(name="STUDENT_DOB")
	private Date studentDOB;

	@Column(name="STUDENT_SKILLSET")
	private String studentSkillSet;


	//default
	public UserEntity(){
		
	}
	public UserEntity(String name, String studentHobby,
			String studentMobileNo,Date studentDOB, String studentSkillSet){
		this.studentName = name;
		this.studentHobby = studentHobby;
		this.studentMobileNo = studentMobileNo;
		this.studentDOB = studentDOB;
		this.studentSkillSet = studentSkillSet;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentHobby() {
		return studentHobby;
	}

	public void setStudentHobby(String studentHobby) {
		this.studentHobby = studentHobby;
	}

	public String getStudentMobileNo() {
		return studentMobileNo;
	}

	public void setStudentMobileNo(String studentMobileNo) {
		this.studentMobileNo = studentMobileNo;
	}

	public Date getStudentDOB() {
		return studentDOB;
	}

	public void setStudentDOB(Date studentDOB) {
		this.studentDOB = studentDOB;
	}

	public String getStudentSkillSet() {
		return studentSkillSet;
	}

	public void setStudentSkillSet(String studentSkillSet) {
		this.studentSkillSet = studentSkillSet;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
