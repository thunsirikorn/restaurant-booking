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

import com.project.Restaurant.model.BookingModel;
import com.project.Restaurant.service.BookingServ;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class BookingControl {

private final BookingServ bookingServ;
	
	public BookingControl(BookingServ bookingServ) {
		this.bookingServ = bookingServ;
	}
	
	@GetMapping("/allbooking")
	public ResponseEntity<List<BookingModel>> getAllBookings() {
		List<BookingModel> bookings = bookingServ.findAllBookings();
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}
	
	@GetMapping("/findbooking/{bookId}")
	public ResponseEntity<BookingModel> getBookingById(@PathVariable("bookId") Long bookId) {
		BookingModel booking = bookingServ.findBooking(bookId);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}
	
	@PostMapping("/addbooking")
	public ResponseEntity<BookingModel> addBooking(@RequestBody BookingModel bookingModel) {
		BookingModel newBooking = bookingServ.addBooking(bookingModel);
		return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
	}
	
	@PutMapping("/updatebooking")
	public ResponseEntity<BookingModel> updateBooking(@RequestBody BookingModel bookingModel) {
		BookingModel updateBooking = bookingServ.updateBooking(bookingModel);
		return new ResponseEntity<>(updateBooking, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletebooking/{bookId}")
	public ResponseEntity<?> deleteBooking(@PathVariable("bookId") Long bookId) {
		bookingServ.deleteBooking(bookId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
