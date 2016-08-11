package model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class User {

	@Id
	private String id;

	private String email;

	private String password;

	private Integer visits;

	private String nickname;

	private Boolean active;

	private Boolean moderator;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date modifiedDate;

	public User() {
		this.visits = 0;
		this.active = true;
		this.moderator = false;
		this.createdDate = new Date();
		this.modifiedDate = new Date();
	}

	public User(String email, String password, String nickname) {
		super();
		this.email = email;
		this.password = password;
		this.visits = 0;
		this.nickname = nickname;
		this.active = true;
		this.moderator = false;
		this.createdDate = new Date();
		this.modifiedDate = new Date();
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

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
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

	public Boolean getModerator() {
		return moderator;
	}

	public void setModerator(Boolean moderator) {
		this.moderator = moderator;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
