import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';

const API_URL = 'http://localhost:8080/test/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8080/auth/alluser')
  }

  public addUser(user: User): Observable<User> {
    return this.http.post<User>('http://localhost:8080/auth/adduser', user);
  }


  public register(user: User): Observable<User> {
  return this.http.post<User>('http://localhost:8080/auth/signup', user);
  }

  public updateUser(user: User): Observable<User> {
    return this.http.put<User>('http://localhost:8080/auth/updateuser', user);
  }

  public deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>('http://localhost:8080/auth/deleteuser/'+userId);
  }



  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUser(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getManager(): Observable<any> {
    return this.http.get(API_URL + 'manager', { responseType: 'text' });
  }

  getAdmin(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

}
