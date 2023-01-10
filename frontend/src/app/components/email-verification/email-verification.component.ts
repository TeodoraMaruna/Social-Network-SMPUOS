import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";
import {UrlParams} from "../../model/url-params";

@Component({
  selector: 'app-email-verification',
  templateUrl: './email-verification.component.html',
  styleUrls: ['./email-verification.component.css']
})
export class EmailVerificationComponent implements OnInit {
  emailConfirmed: Boolean = false;
  urlParams: UrlParams = new UrlParams()

  constructor(private route: ActivatedRoute, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    // @ts-ignore
    this.urlParams.token = this.route.snapshot.queryParamMap.get('token');
    // @ts-ignore
    this.urlParams.username = this.route.snapshot.queryParamMap.get('username');
    this.confirmEmail();
  }

  private confirmEmail() {

    this.authService.confirmEmail(this.urlParams).subscribe(
      ()=>{
        this.emailConfirmed = true;
        console.log("uslo")
      },
      (error) => {
        this.emailConfirmed = false;
      }
    )
  }

  goToLogin() {
    this.router.navigate(['login'])
  }
}
