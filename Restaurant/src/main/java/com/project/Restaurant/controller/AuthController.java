package com.project.Restaurant.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Restaurant.model.ERole;
import com.project.Restaurant.model.Role;
import com.project.Restaurant.model.UserModel;
import com.project.Restaurant.payload.request.LoginRequest;
import com.project.Restaurant.payload.request.SignupRequest;
import com.project.Restaurant.payload.response.JwtResponse;
import com.project.Restaurant.payload.response.MessageResponse;
import com.project.Restaurant.security.services.UserDetailsImp;
import com.project.Restaurant.repository.UserRepos;
import com.project.Restaurant.repository.RoleRepos;
import com.project.Restaurant.security.jwt.JwtUtils;


//@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepos userRepos;
	
	@Autowired
	RoleRepos roleRepos;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,
								userDetails.getUserId(),
								userDetails.getUsername(),
								userDetails.getFirstname(),
								userDetails.getLastname(),
								userDetails.getEmail(),
								roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
		if (userRepos.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		
		if(userRepos.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		
		//Create new user account
		UserModel userModel = new UserModel(
				signupRequest.getUsername(),
				encoder.encode(signupRequest.getPassword()),
				signupRequest.getFirstname(),
				signupRequest.getLastname(),
				signupRequest.getBirthday(),
				signupRequest.getGender(),
				signupRequest.getTelephone(),
				signupRequest.getEmail());
		
		Set<String> strRoles = signupRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		if(strRoles == null) {
			Role userRole = roleRepos.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepos.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
					
				case "manager":
					Role managerRole = roleRepos.findByName(ERole.ROLE_MANAGER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(managerRole);
					break;
					
				default:
					Role userRole = roleRepos.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		
		userModel.setRoles(roles);
		userRepos.save(userModel);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
