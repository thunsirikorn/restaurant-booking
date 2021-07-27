package com.project.Restaurant.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Restaurant.model.UserModel;
import com.project.Restaurant.repository.UserRepos;


@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	UserRepos userRepos;
	
//	@Override
	@Transactional
	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
		UserModel userModel = userRepos.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + email));
		return UserDetailsImp.build(userModel);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserModel userModel = userRepos.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + email));
		return UserDetailsImp.build(userModel);
	}
}
