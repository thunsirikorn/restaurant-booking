package com.project.Restaurant.security.services;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.Restaurant.model.UserModel;


public class UserDetailsImp implements UserDetails {
private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String username;
	
	@JsonIgnore
	private String password;
	
	private String firstname;
	private String lastname;
	private Date birthday;
	private String gender;
	private String telephone;
	private String email;
	
	private Collection<? extends GrantedAuthority> authorities;

	
	public UserDetailsImp(Long userId, String username, String password, String firstname, String lastname,
			Date birthday, String gender, String telephone, String email,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
		this.authorities = authorities;
	}


	public static UserDetailsImp build(UserModel userModel) {
		List<GrantedAuthority> authorities = userModel.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
				.collect(Collectors.toList());
		
		return new UserDetailsImp(
			userModel.getUserId(),
			userModel.getUsername(),
			userModel.getPassword(),
			userModel.getFirstname(),
			userModel.getLastname(),
			userModel.getBirthday(),
			userModel.getGender(),
			userModel.getTelephone(),
			userModel.getEmail(),
			authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public String getEmail() {
		return email;
	}
	
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImp userModel = (UserDetailsImp) o;
			return Objects.equals(userId, userModel.userId);
	}
}
