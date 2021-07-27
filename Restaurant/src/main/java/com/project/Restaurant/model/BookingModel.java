package com.project.Restaurant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
@Table(name="booking")
public class BookingModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long bookId;
	private String bookName;
	private String bookDate;
	private String bookTime;
	private String person;
	private String occasion;
	private String telephone;
	private String email;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resId")
	@Fetch(FetchMode.JOIN)
	private RestaurantModel restaurantModel;
	
	public BookingModel() {}

	public BookingModel(String bookName, String bookDate, String bookTime, String person, String occasion,
			String telephone, String email, RestaurantModel restaurantModel) {
		super();
		this.bookName = bookName;
		this.bookDate = bookDate;
		this.bookTime = bookTime;
		this.person = person;
		this.occasion = occasion;
		this.telephone = telephone;
		this.email = email;
		this.restaurantModel = restaurantModel;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public String getBookTime() {
		return bookTime;
	}

	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getOccasion() {
		return occasion;
	}

	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RestaurantModel getRestaurantModel() {
		return restaurantModel;
	}

	public void setRestaurantModel(RestaurantModel restaurantModel) {
		this.restaurantModel = restaurantModel;
	}
	

}
