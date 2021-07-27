import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Restaurant } from 'src/app/models/restaurant.model';
import { User } from 'src/app/models/user.model';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public restaurants: Restaurant[];
  public users: User[];

  private roles: string[] = [];

  isSignin = false;
  showAdmin = false;
  showManager = false;
  email?: string;


  constructor(
    public restaurantService: RestaurantService,
    public userService: UserService,
    private tokenStorageService: TokenStorageService,
    private router: Router) { }

  ngOnInit(): void {
    this.getRestaurants();

    this.isSignin = !!this.tokenStorageService.getToken();

    if(this.isSignin) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdmin = this.roles.includes('ROLE_ADMIN');
      this.showManager = this.roles.includes('ROLE_MANAGER');

      this.email = user.email;
    }
  }

  public getRestaurants(): void {
    this.restaurantService.getRestaurants().subscribe(
      (response: Restaurant[]) => {
        this.restaurants = response;
        console.log(this.restaurants);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
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

  
  signout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
    this.router.navigate([''])
  }

}
