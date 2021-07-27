package com.project.Restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Restaurant.exception.UserNotFoundException;
import com.project.Restaurant.model.BookingModel;
import com.project.Restaurant.repository.BookingRepos;


@Service
@Transactional
public class BookingServ {
	
private final BookingRepos bookingRepos;
	
	@Autowired
	public BookingServ(BookingRepos bookingRepos) {
		this.bookingRepos = bookingRepos;
	}
	
	public BookingModel addBooking(BookingModel bookingModel) {
		return bookingRepos.save(bookingModel);
	}
	
	public List<BookingModel> findAllBookings() {
		return bookingRepos.findAll();
	}
	
	public BookingModel updateBooking(BookingModel bookingModel) {
		return bookingRepos.save(bookingModel);
	}
	
	public BookingModel findBooking(Long bookId) {
		return bookingRepos.findById(bookId)
				.orElseThrow(() -> new UserNotFoundException("Booking by id " + bookId + " was not found"));
	}
	
	public void deleteBooking(Long bookId) {
		bookingRepos.deleteById(bookId);
	}

}
