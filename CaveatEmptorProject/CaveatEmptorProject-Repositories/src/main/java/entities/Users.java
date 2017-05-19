package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import Constants.Constants;

@Entity
@NamedQueries({ @NamedQuery(name = Constants.FIND_USER_BY_USERNAME, query = Constants.FIND_USER_BY_USERNAME_QUERY),
				@NamedQuery(name = Constants.FIND_USER_BY_EMAIL, query = Constants.FIND_USER_BY_EMAIL_QUERY) 
})

public class Users implements Serializable {

	private static final long serialVersionUID = 3436918407169441316L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;

	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private int ranking = 0;
	@Column
	private boolean admin = false;
	@Column
	private String phoneNumber;

	@Column
	private boolean enabled = false;

	public Users(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Users() {
	}

	public Long getId() {
		return user_id;
	}

	public void setId(Long user_id) {
		this.user_id = user_id;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
