import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { UserService } from './services/user.service';
import { RestaurantService } from './services/restaurant.service';
import { UserComponent } from './components/user/user.component';
import { RestaurantComponent } from './components/restaurant/restaurant.component';
import { BookingComponent } from './components/booking/booking.component';
import { RestaurantdtComponent } from './components/restaurantdt/restaurantdt.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { ProfileComponent } from './components/profile/profile.component';
import { SignupComponent } from './components/signup/signup.component';
import { SigninComponent } from './components/signin/signin.component';
import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { BoardManagerComponent } from './components/board-manager/board-manager.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    UserComponent,
    RestaurantComponent,
    BookingComponent,
    RestaurantdtComponent,
    ProfileComponent,
    SignupComponent,
    SigninComponent,
    BoardAdminComponent,
    BoardManagerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  providers: [
    UserService,
    RestaurantService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
