package com.project.Restaurant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Restaurant.model.UserModel;
import com.project.Restaurant.service.UserServ;


//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class UserControl {
	
	private UserServ userServ;
	
	public UserControl(UserServ userServ) {
		this.userServ = userServ;
	}
	
	@GetMapping("/alluser")
	public ResponseEntity<List<UserModel>> getAllUsers() {
		List<UserModel> users = userServ.findAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/finduser/{userId}")
	public ResponseEntity<UserModel> getUserById(@PathVariable("userId") Long userId) {
		UserModel user = userServ.findUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<UserModel> addUser(@RequestBody UserModel userModel) {
		UserModel newUser = userServ.addUser(userModel);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel) {
		UserModel updateUser = userServ.updateUser(userModel);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
		userServ.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
