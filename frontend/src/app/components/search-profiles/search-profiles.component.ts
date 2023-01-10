import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';
import { ConnectionService } from 'src/app/service/connection.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-search-profiles',
  templateUrl: './search-profiles.component.html',
  styleUrls: ['./search-profiles.component.css']
})
export class SearchProfilesComponent implements OnInit {

  constructor(private userService: UserService, private authService: AuthService, private connectionService: ConnectionService,
     private router: Router) { }

  users: User[] = [];
  searchedUsers: User[] = [];
  searchCriteria: string = "";
  loggedIn: boolean = false;
  visibleUserAcccountSettings: boolean = false;

  ngOnInit(): void {
    this.determineIfUserLoggedIn();
  }

  determineIfUserLoggedIn(){
    this.loggedIn = this.authService.isLoggedIn()

    if (this.loggedIn){
      this.loadAllowedUsers()
    }
    else {
      this.loadUsers();
    }
    console.log("is user logged in", this.loggedIn)
  }

  loadUsers(){
    this.userService.getAll().subscribe(
      (data: any) => {
        this.users = []
        this.users=data
        console.log(this.users)
      },(error) => {

        console.log("error")
      }
      )
  }

  loadAllowedUsers(){
    let username = this.authService.getUsername();
    if (username != null && username != undefined){
      this.connectionService.allowedUserConnections(username).subscribe(
        (data: any) => {
          this.users = []
          this.users=data
        })
    }
  }

  seeProfile(username: String){
    if (username != undefined){
      this.router.navigate(['/user-profile', username]);
    }
  }

  clear(){
    this.searchedUsers = []
    this.searchCriteria = "";
  }

  search(){
    this.searchedUsers = this.users.filter(u =>
      (u.username).toLowerCase().includes(this.searchCriteria.toLowerCase()) ||
      (u.firstName).toLowerCase().includes(this.searchCriteria.toLowerCase()) ||
      (u.lastName).toLowerCase().includes(this.searchCriteria.toLowerCase()) ||
      (u.email).toLowerCase().includes(this.searchCriteria.toLowerCase())
    )
  }

  makeVisibleUserAcccountSettings() {
    this.visibleUserAcccountSettings = !this.visibleUserAcccountSettings
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
