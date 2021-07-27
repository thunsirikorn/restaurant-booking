package com.project.Restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Restaurant.model.UserModel;


@Repository
public interface UserRepos extends JpaRepository<UserModel, Long>{

//	void deleteUserById(Long user_id);
	
//	Optional<UserModel> findUserById(Long user_id);
	
	Optional<UserModel> findByEmail(String email);
	Boolean existsByEmail(String email);
	Boolean existsByUsername(String username);

}
