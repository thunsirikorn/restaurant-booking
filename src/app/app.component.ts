import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'restaurant';

  private roles: string[] = [];

  isSignin = false;
  showAdmin = false;
  showManager = false;
  email?: string;
  

  constructor(
    private tokenStorageService: TokenStorageService,
    private router: Router) { }

  ngOnInit(): void {
    this.isSignin = !!this.tokenStorageService.getToken();

    if(this.isSignin) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdmin = this.roles.includes('ROLE_ADMIN');
      this.showManager = this.roles.includes('ROLE_MANAGER');

      this.email = user.email;
    }
  }

  signout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
    this.router.navigate([''])
  }

}
