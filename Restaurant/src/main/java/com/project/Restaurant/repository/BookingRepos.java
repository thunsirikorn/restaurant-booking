package com.project.Restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Restaurant.model.BookingModel;


@Repository
public interface BookingRepos extends JpaRepository<BookingModel, Long> {

}
