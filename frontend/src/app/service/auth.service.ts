import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly userPath = environment.apiUrl + 'auth-service/login';
  private access_token: any;

  constructor() { }
}
