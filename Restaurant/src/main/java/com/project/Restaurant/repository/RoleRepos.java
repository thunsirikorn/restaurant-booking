package com.project.Restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Restaurant.model.ERole;
import com.project.Restaurant.model.Role;


@Repository
public interface RoleRepos extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole roleName);
}
