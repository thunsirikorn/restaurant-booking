package com.project.Restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Restaurant.model.RestaurantModel;

@Repository
public interface RestaurantRepos extends JpaRepository<RestaurantModel, Long> {

//	void deleteRestaurantById(Long resId);

//	Optional<RestaurantModel> findRestaurantById(Long resId);


}
