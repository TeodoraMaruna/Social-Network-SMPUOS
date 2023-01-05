import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {User} from "../model/user";
import {map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {UserTokenState} from "../model/user-token-state";
import {JwtUtilsService} from "./jwt-utils.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly userPath = environment.apiUrl + '/auth-service';
  private access_token: string = '';

  constructor(private _http:HttpClient, private jwtUtilsService: JwtUtilsService) { }

  public login(user:User):Observable<void>{

    const body = {
      username: user.username,
      password: user.password
    };

    console.log(body)
    return this._http.post<UserTokenState>(`http://localhost:9000/auth-service/login`,body)
      .pipe(map((res) => {
        console.log("uslo")
        console.log(res)
        const username = body.username;
        this.access_token = res.accessToken;
        const token = this.access_token;
        localStorage.setItem('currentUser', JSON.stringify({
          username,
          role: this.jwtUtilsService.getRoles(token),
          token
        }));
      }));
  }

  isLoggedIn(): boolean {
    return this.getToken() !== '';
  }

  getToken(): string {
    // @ts-ignore
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const token = currentUser && currentUser.token;
    return token ? token : '';
  }

  logout() {
    localStorage.removeItem('currentUser');
    localStorage.clear();
  }

  getUsername(): string{
    return this.getCurrentUser().username;
  }

  getCurrentUser(): any {
    if (localStorage['currentUser']) {
      return JSON.parse(localStorage['currentUser']);
    } else {
      return undefined;
    }
  }
}
