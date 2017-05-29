package com.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Size;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -5140878302612717434L;
	@Size(min = 3)
	private Long userId;
	
	@Size(min = 3)
	private String firstname;
	
	@Size(min = 3)
	private String lastname;
	
	@Size(min = 3)
	private String username;
	
	@Size(min = 3)
	private String password;
	
	@Size(min = 10)
	private String email;
	
	@Size(min = 3)
	private int ranking;
	
	@Size(min = 3)
	private boolean admin;
	
	@Size(min = 10)
	private String phoneNumber;
	
	@Size(min = 3)
	private boolean enabled;
	
	@Size(min = 3)
	private String activationKey;
	
	@Size(min = 3)
	private Timestamp dateRegistered;

	public UserDTO() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public Timestamp getDateRegistered() {
		return new Timestamp(dateRegistered.getTime());
	}

	public void setDateRegistered(Timestamp dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

}
