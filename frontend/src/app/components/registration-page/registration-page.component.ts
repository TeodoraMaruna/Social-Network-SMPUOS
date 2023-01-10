import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

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

  constructor(fb: FormBuilder, private _authService: AuthService,private _router: Router,
              private _snackBar: MatSnackBar) {
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
    if (this.password != this.user.password){
      this._snackBar.open("Password does not match", "Error");
    }
    else {
      console.log(this.user)
      this._snackBar.open("Wait a minute", "In progress");
      this._authService.register(this.user).subscribe(
        (user:User) =>{
            console.log("uspjeh")
          console.log(user)
            if(user.sagaStatus === 'FAILED' || user.sagaStatus === null){
              this._snackBar.open("Somenthing went wrong", "Error!");
              return;
            }
          this._snackBar.open("You are registred", "Success");
          this._router.navigate(['login']);
        },
        (error) =>{
          this._snackBar.open("Somenthing went wrong", "Error!");
        })
    }
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
