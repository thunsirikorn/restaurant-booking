package com.project.Restaurant.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.Restaurant.exception.UserNotFoundException;
import com.project.Restaurant.model.RestaurantModel;
import com.project.Restaurant.repository.RestaurantRepos;

@Service
@Transactional
public class RestaurantServ {
	
	private final RestaurantRepos restaurantRepos;
	
	@Autowired
	public RestaurantServ(RestaurantRepos restaurantRepos) {
		this.restaurantRepos = restaurantRepos;
	}

/*
	public RestaurantModel addRestaurant(RestaurantModel restaurantModel) {
		return restaurantRepos.save(restaurantModel);
	}
*/
	public RestaurantModel addRestaurant(RestaurantModel restaurantModel) {
		return restaurantRepos.save(restaurantModel);
	}
	
	public List<RestaurantModel> findAllRestaurants() {
		return restaurantRepos.findAll();
	}
	
	public RestaurantModel updateRestaurant(RestaurantModel restaurantModel) {
		return restaurantRepos.save(restaurantModel);
	}
	
	public RestaurantModel findRestaurantById(Long resId) {
		return restaurantRepos.findById(resId)
				.orElseThrow(() -> new UserNotFoundException("Restaurant by id " + resId + " was not found"));
	}
	
	public void deleteRestaurant(Long resId) {
		restaurantRepos.deleteById(resId);
	}

	public Optional<RestaurantModel> findByName(String imageURL) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<RestaurantModel> findImageById(Long resId) {
		// TODO Auto-generated method stub
		return Optional.of(restaurantRepos.findById(resId)
				.orElseThrow(() -> new UserNotFoundException("Restaurant by id " + resId + " was not found")));
	}

}
