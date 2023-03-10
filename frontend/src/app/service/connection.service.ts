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

  public findPostsFromFollowers(username: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/postsFromFollowers/` + username)
  }

  public findSentFollowRequestsForUsername(username: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/sentFollowRequests/` + username)
  }

  public findBlockedForUsername(username: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/findBlocked/` + username)
  }

  public findRecommended(username: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/findRecommended/` + username)
  }

  public editUser(user: UserConnection): Observable<any> {
    return this._http.put<any>(environment.apiUrl + `${this.userPath}/editUser`, user)
  }

  public blockUser(conn: CreateConnection): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/blockUser`, conn)
  }

  public approveFollowRequest(conn: CreateConnection): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/approveFollowRequest`, conn)
  }

  public rejectFollowRequest(conn: CreateConnection): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/rejectFollowRequest`, conn)
  }

  public createConnection(conn: CreateConnection): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/connection`, conn)
  }

  public checkIfUsersFollowEachOther(conn: CreateConnection): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/checkIfUsersFollowEachOther`, conn)
  }

  public checkIfUserSentFollowRequest(conn: CreateConnection): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/checkIfUserSentFollowRequest`, conn)
  }

  public removeFollower(conn: CreateConnection): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/removeFollower`, conn)
  }

  public removeBlocked(conn: CreateConnection): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/removeBlocked`, conn)
  }

  public removeBlockedBy(conn: CreateConnection): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/removeBlockedBy`, conn)
  }

  public allowedUserConnections(username: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/allowedUserConnections/` + username)
  }

}
