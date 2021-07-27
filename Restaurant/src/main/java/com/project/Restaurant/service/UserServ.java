package com.project.Restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Restaurant.exception.UserNotFoundException;
import com.project.Restaurant.model.UserModel;
import com.project.Restaurant.repository.UserRepos;

@Service
@Transactional
public class UserServ {
private UserRepos  userRepos;
	
	@Autowired
	public UserServ(UserRepos userRepos) {
		this.userRepos = userRepos;
	}
	
	public UserModel addUser(UserModel userModel) {
		return userRepos.save(userModel);
	}
	
	public List<UserModel> findAllUsers() {
		return userRepos.findAll();
	}
	
	public UserModel updateUser(UserModel userModel) {
		return userRepos.save(userModel);
	}
	
	public UserModel findUserById(Long userId) {
		return userRepos.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User by id " + userId + " was not found"));
	}
	
	public void deleteUser(Long userId) {
		userRepos.deleteById(userId);
	}

}
