import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {Observable} from "rxjs";
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  private readonly userPath = '/user-service';

  constructor(private _http: HttpClient) {
  }

  public getUserByUsername(username: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/findByUsername/` + username)
  }

  public getAll(): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/findAll`)
  }

  public editUser(user: User): Observable<any> {
    return this._http.put<any>(environment.apiUrl + `${this.userPath}/edit`, user)
  }

}
