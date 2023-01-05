import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CreateConnection } from '../model/create-connection';
import { User } from '../model/user';
import { UserConnection } from '../model/user-connection';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  private readonly userPath = '/connection-service';

  constructor(private _http: HttpClient) {
  }

  public findFollowersForUsername(username: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/followers/` + username)
  }

  public findFollowRequestsForUsername(username: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/followRequests/` + username)
  }

  public findRecommendedForUsername(username: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/findRecommended/` + username)
  }

  public findBlockedForUsername(username: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/findBlocked/` + username)
  }

  public editUser(user: UserConnection): Observable<any> {
    return this._http.put<any>(environment.apiUrl + `${this.userPath}/editUser`, user)
  }

  public blockUser(conn: CreateConnection): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/blockUser`, conn)
  }
}
