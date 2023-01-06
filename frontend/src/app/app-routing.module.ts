import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./components/home-page/home-page.component";
import { LoginPageComponent } from './components/login-page/login-page.component';
import {PostPageComponent} from "./components/post-page/post-page.component";
import {UserFeedComponent} from "./components/user-feed/user-feed.component";
import { RegistrationPageComponent } from './components/registration-page/registration-page.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';

const routes: Routes = [
  {
    path: 'registration', component: RegistrationPageComponent
  },
  {
    path: 'login', component: LoginPageComponent
  },
  {
    path: 'user-feed', component: UserFeedComponent
  },
  {
    path: 'user-profile/:username', component: UserProfileComponent
  }
]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

