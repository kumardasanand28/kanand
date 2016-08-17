package model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


public class User {

	@Id
	private String id;
	
	private String name;

	private String email;
	
	private String password;

	private String nickname;

	private Boolean active;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;

	public User() {
		this.active = true;
		this.createdDate = new Date();
	}
	
	

	public User(String name,String email, String password, String nickname) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.active = true;
		this.createdDate = new Date();
	}

	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
