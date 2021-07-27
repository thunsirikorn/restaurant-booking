import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

//  roles = ["admin", "manager", "user"];

  signup: any = {
    username: '',
    password: '',
    firstname: '',
    lastname: '',
    birthday: new Date,
    gender: '',
    telephone: '',
    email: '',
//    role: ["user"]
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  


  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }


  onSubmit(): void {
    const { username, password, firstname, lastname, birthday, gender, telephone, email } = this.signup;

    this.authService.register(username, password, firstname, lastname, birthday, gender, telephone, email).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.message;
        this.isSignUpFailed = true;
      }
    );
  }


}
