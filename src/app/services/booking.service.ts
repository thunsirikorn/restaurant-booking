import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Booking } from '../models/booking.model';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private apiServerUrl = environment.apiBaseUrl;
  
  constructor(private http: HttpClient) { }

  public getBookings(): Observable<Booking[]> {
    return this.http.get<Booking[]>('http://localhost:8080/auth/allbooking')
  }

  public getBookingById(bookId: number): Observable<any> {
    return this.http.get('http://localhost:8080/auth/findbooking/'+bookId)
  }

  public addBooking(booking: Booking): Observable<Booking> {
    return this.http.post<Booking>('http://localhost:8080/auth/addbooking', booking);
  }

  public updateBooking(booking: Booking): Observable<Booking> {
    return this.http.put<Booking>('http://localhost:8080/auth/updatebooking', booking);
  }

  public deleteBooking(bookId: number): Observable<void> {
    return this.http.delete<void>('http://localhost:8080/auth/deletebooking/'+bookId);
  }

}
