package com.project.Restaurant.payload.request;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class SignupRequest {

	private Long userId;
	
	private String username;
	
	@Size(min = 6, max = 30)
	private String password;
	
	@Size(min = 3)
	private String firstname;
	
	@Size(min = 3)
	private String lastname;
	
	private Date birthday;
	
	private String gender;
	
	private String telephone;
	
	@Size(max = 70)
	@Email
	private String email;
	
	private Set<String> role;

	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	 public Set<String> getRole() {
	      return this.role;
	    }
	    
	 public void setRole(Set<String> role) {
	      this.role = role;
	    }
}
