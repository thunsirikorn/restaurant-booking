import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Booking } from 'src/app/models/booking.model';
import { Restaurant } from 'src/app/models/restaurant.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { BookingService } from 'src/app/services/booking.service';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public restaurants: Restaurant[];
  public detailRestaurant: Restaurant;
  public users: User[];
  public bookings: Booking[];
  public currentRestaurant: Restaurant;
  public currentId = -1;

  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  imageURL: any;
  resId: number;

  content?: string;


  constructor(
    public restaurantService: RestaurantService,
    public userService: UserService,
    public bookingService: BookingService,
    private route: ActivatedRoute ) { }

  totalLength: number;
  page: number = 1;


  ngOnInit(): void {
    this.userService.getPublicContent().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
    this.getRestaurants();
  }

  public getRestaurants(): void {
    this.restaurantService.getRestaurants().subscribe(
      (response: Restaurant[]) => {
        this.restaurants = response;
        this.retrieveResonse = response;
          this.base64Data = this.retrieveResonse.resImage;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        this.totalLength = response.length;
        console.log(this.restaurants);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  refreshList(): void {
    this.getRestaurants();
    this.currentRestaurant = undefined;
    this.currentId = -1;
  }

  setActiveRestaurant(restaurant: Restaurant, resId: number): void {
    this.currentRestaurant = restaurant;
    this.currentId = resId;
  }
  

  public searchRestaurants(key: string): void {
    console.log(key);
    const results: Restaurant[] = [];
    for (const restaurant of this.restaurants) {
      if (restaurant.resName.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || restaurant.canton.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || restaurant.district.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || restaurant.province.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(restaurant);
      }
    }
    this.restaurants = results;
    if (results.length === 0 || !key) {
      this.getRestaurants();
    }
  }

  public onSignup(addForm: NgForm): void {
    document.getElementById('add-user-form').click();
    this.userService.addUser(addForm.value).subscribe(
        (response: User) => {
        console.log(response);
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }


  public onSignin(addForm: NgForm): void {
    document.getElementById('user-signin-form').click();
    this.userService.addUser(addForm.value).subscribe(
      (response: User) => {
        console.log(response);
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }



  public onBooking(addForm: NgForm): void {
    document.getElementById('user-booking-form').click();
    this.bookingService.addBooking(addForm.value).subscribe(
      (response: Booking) => {
        console.log(response);
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );

  }

  
  public onOpenModal(user: User, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'signup') {
      button.setAttribute('data-target', '#userSignupModal');
    }
    if (mode === 'signin') {
      button.setAttribute('data-target', '#userSigninModal');
    }

    container.appendChild(button);
    button.click();
  }

  public onOpenModall(restaurant: Restaurant, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
   
    if (mode === 'booking') {
      button.setAttribute('data-target', '#userBookingModal');
    }

    container.appendChild(button);
    button.click();
  }

}
