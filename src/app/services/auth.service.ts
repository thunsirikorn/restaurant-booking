import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


const AUTH_API = 'http://localhost:8080/auth/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  signin(email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      email,
      password
    }, httpOptions);
  }

  register(
    username: string,
    password: string,
    firstname: string,
    lastname: string,
    birthday: Date,
    gender: string,
    telephone: string,
    email: string,
//    role: string
     ): Observable<any> {
      return this.http.post(AUTH_API + 'signup', {
        username,
        password,
        firstname,
        lastname,
        birthday,
        gender,
        telephone,
        email,
//        role
      }, httpOptions)
    }

}
