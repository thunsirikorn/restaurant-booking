import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Booking } from 'src/app/models/booking.model';
import { Restaurant } from 'src/app/models/restaurant.model';
import { BookingService } from 'src/app/services/booking.service';
import { RestaurantService } from 'src/app/services/restaurant.service';

@Component({
  selector: 'app-restaurantdt',
  templateUrl: './restaurantdt.component.html',
  styleUrls: ['./restaurantdt.component.css']
})
export class RestaurantdtComponent implements OnInit {

  public restaurants: Restaurant;
  public bookings: Booking[];


  constructor(
    private restaurantService: RestaurantService,
    private bookingService: BookingService,
    private route: ActivatedRoute,
    private router: Router ) { }

  ngOnInit(): void {
    this.getRestaurantId(this.route.snapshot.params.resId)
//    this.onBooking(this.route.snapshot.params.resId)
  }


  public getRestaurantId(resId: number): void {
    this.restaurantService.getRestaurantById(resId).subscribe(
      data => {
        this.restaurants = data;
        console.log(data);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
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

  public onOpenModal(restaurant: Restaurant, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if(mode === 'add') {
      button.setAttribute('data-target', '#addRestaurantModal');
    }
    if (mode === 'booking') {
      button.setAttribute('data-target', '#userBookingModal');
    }
    
    container.appendChild(button);
    button.click();
  }

}
