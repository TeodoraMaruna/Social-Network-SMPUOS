import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private readonly userPath = '/post-service';

  constructor(private _http: HttpClient) {
  }


  public saveComment(): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}`)
  }

}
