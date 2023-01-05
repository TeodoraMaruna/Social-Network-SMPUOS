import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor(private _authService: AuthService,  private _router: Router) { }

  user: User = new User();

  ngOnInit(): void {
  }

  login(){

    console.log(this.user)
    this._authService.login(this.user).subscribe(
      () => {
        console.log("uspeh")
        this._router.navigate(['user-feed']);
        console.log(this._authService.getUsername())
      },
      (error)=> {
        console.log('error occuried');
        // this.alertService.danger('Pogresni kredencijali');
      }
    )
  }



  register() {

  }
}
