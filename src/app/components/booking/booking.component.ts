import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Booking } from 'src/app/models/booking.model';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  public bookings: Booking[];
  public editBooking: Booking;
  public deleteBooking: Booking;

  constructor(private bookingService: BookingService) { }

  totalLength: number;
  page: number = 1;

  ngOnInit(): void {
    this.getBookings();
  }

  public getBookings(): void {
    this.bookingService.getBookings().subscribe(
      (response: Booking[]) => {
        this.bookings = response;
        this.totalLength = response.length;
        console.log(this.bookings);
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

  public onUpdateBooking(booking: Booking): void {
    this.bookingService.updateBooking(booking).subscribe(
      (response: Booking) => {
        console.log(response);
        this.getBookings();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteBooking(bookId: number): void {
    this.bookingService.deleteBooking(bookId).subscribe(
      (response: void) => {
        console.log(response);
        this.getBookings();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchBookings(key: string): void {
    console.log(key);
    const results: Booking[] = [];
    for (const booking of this.bookings) {
      if (booking.bookName.toLowerCase().indexOf(key.toLowerCase()) !== -1
       || booking.bookDate.toLowerCase().indexOf(key.toLowerCase()) !== -1
       || booking.bookTime.toLowerCase().indexOf(key.toLowerCase()) !== -1
       || booking.telephone.toLowerCase().indexOf(key.toLowerCase()) !== -1
       || booking.email.toLowerCase().indexOf(key.toLowerCase()) !== -1)
       {     
       results.push(booking);
      }
    }
    this.bookings = results;
    if(results.length === 0 || !key) {
      this.getBookings();
    }
  }

  public onOpenModal(booking: Booking, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'booking') {
      button.setAttribute('data-target', '#userBookingModal');
    }
    if(mode === 'edit') {
      this.editBooking = booking;
      button.setAttribute('data-target', '#updateBookingModal');
    }
    if(mode === 'delete') {
      this.deleteBooking = booking;
      button.setAttribute('data-target', '#deleteBookingModal');
    }
    container.appendChild(button);
    button.click();
  }

}
