import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./components/home-page/home-page.component";
import { LoginPageComponent } from './components/login-page/login-page.component';
import {UserFeedComponent} from "./components/user-feed/user-feed.component";
import { RegistrationPageComponent } from './components/registration-page/registration-page.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { SearchProfilesComponent } from './components/search-profiles/search-profiles.component';
import {EmailVerificationComponent} from "./components/email-verification/email-verification.component";
import {AuthGuard} from "./auth.guard";

const routes: Routes = [
  {
    path: 'registration', component: RegistrationPageComponent
  },
  {
    path: 'login', component: LoginPageComponent
  },
  {
    path: 'user-feed', component: UserFeedComponent, canActivate: [AuthGuard]
  },
  {
    path: 'user-profile/:username', component: UserProfileComponent
  },
  {
    path: 'search-profiles', component: SearchProfilesComponent
  },
  {
    path: 'email-verification', component: EmailVerificationComponent
  }
]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

