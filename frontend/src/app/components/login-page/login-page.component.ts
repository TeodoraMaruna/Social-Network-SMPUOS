import { Component, OnInit } from '@angular/core';
import { MyUser } from 'src/app/model/my-user';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor() { }

  user: MyUser = new MyUser();

  ngOnInit(): void {
  }

  login(){}

}
