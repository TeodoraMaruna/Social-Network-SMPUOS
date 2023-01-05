import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';
import { AuthService } from 'src/app/service/auth.service';
import { ConnectionService } from 'src/app/service/connection.service';
import { UserConnection } from 'src/app/model/user-connection';

@Component({
  selector: 'app-update-info',
  templateUrl: './update-info.component.html',
  styleUrls: ['./update-info.component.css']
})
export class UpdateInfoComponent implements OnInit {
  
  user: User = new User();
  userConnection: UserConnection = new UserConnection();
  options: FormGroup;
  genderControl: any;

  constructor(public dialogRef: MatDialogRef<UpdateInfoComponent>, fb: FormBuilder, private authService: AuthService, 
    private userService: UserService, private connectionService: ConnectionService) {
    this.options = fb.group({
      gender: this.genderControl
    });
  }

  ngOnInit(): void {
    this.genderControl = new FormControl('Female');
    this.loadUserData(); 
  }

  loadUserData(){
    this.user.username =  this.authService.getUsername();

    if (this.user.username != undefined){
      this.userService.getUserByUsername(this.user.username).subscribe(
        (user: any) => {
        this.user = user
        this.genderControl = new FormControl(this.user.gender);  
      })
    }
  }

  publicUser(){
    this.user.isPublic = true
  }

  privateUser(){
    this.user.isPublic = false
  }

  cancel() {
    this.dialogRef.close();
  }

  update() {
    this.user.gender = this.genderControl.value
    this.updateUserService()
    this.updateConnectionService()
    // TODO: dodati i za auth service (ako ima) !!!
    this.dialogRef.close();
  }

  updateUserService(){
    this.userService.editUser(this.user).subscribe()
  }

  updateConnectionService(){
    this.userConnection.username = this.user.username
    this.userConnection.public = this.user.isPublic
    this.connectionService.editUser(this.userConnection).subscribe()
  }
}
