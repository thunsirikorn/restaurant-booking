import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Restaurant } from '../models/restaurant.model';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private apiServerUrl = environment.apiBaseUrl;
  
  
  constructor(private http: HttpClient) { }

  public getRestaurants(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>('http://localhost:8080/auth/allrest')
  }

  public getRestaurantById(resId: number): Observable<any> {
    return this.http.get('http://localhost:8080/auth/findrest/'+resId)
  }

  public addRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    return this.http.post<Restaurant>('http://localhost:8080/auth/addrest', restaurant);
  }

  public updateRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    return this.http.put<Restaurant>('http://localhost:8080/auth/updaterest', restaurant);
  }

  public deleteRestaurant(restaurantId: number): Observable<void> {
    return this.http.delete<void>('http://localhost:8080/auth/deleterest/'+restaurantId);
  }

  public getImageByName(restaurantId: number): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>('http://localhost:8080/auth/image'+restaurantId);
  }

}
