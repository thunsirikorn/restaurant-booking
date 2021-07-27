package com.project.Restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleId", nullable = false)
	private Long roleId;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30, name="roleName")
	private ERole name;
	
	public Role() { }
	
	public Role(ERole roleName) {
		this.name = roleName;
	}
	
	public Long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public ERole getRoleName() {
		return name;
	}
	
	public void setRoleName(ERole roleName) {
		this.name = roleName;
	}

}
