import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {
  stringBirthday: any;
  message: any;
  options: FormGroup;
  genderControl: any;
  birth: any;
  password: string = '';
  user: User = new User()

  constructor(fb: FormBuilder, private _authService: AuthService,private _router: Router) {
    this.options = fb.group({
      gender: this.genderControl
    });
  }

  ngOnInit(): void {
    this.genderControl = new FormControl('Female');
  }


  selectGender(e: any){
    console.log(e.target.value)
    var gender = e.target.value
    if(gender === 'Male'){
      this.user.gender = "Male"
    }else if(gender === 'Female'){
      this.user.gender = "Female"
    }

  }
  signup() {
    console.log(this.user)
    this._authService.register(this.user).subscribe(
      (user:User) =>{
          console.log("uspjeh")
        this._router.navigate(['login']);
      },
      (error) =>{

      })

  }

  selectVisibility(e: any) {
    console.log(e.target.value)
    var visibility = e.target.value
    if(visibility === 'Private'){
      this.user.isPublic = false
    }else if(visibility === 'Public'){
      this.user.isPublic = true
    }
  }
}
