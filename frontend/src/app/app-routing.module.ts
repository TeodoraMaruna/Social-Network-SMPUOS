import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./components/home-page/home-page.component";
import { LoginPageComponent } from './components/login-page/login-page.component';
import {PostPageComponent} from "./components/post-page/post-page.component";
import { RegistrationPageComponent } from './components/registration-page/registration-page.component';

const routes: Routes = [
  {
    path: '', component: HomePageComponent
  },
  {
    path: 'registration', component: RegistrationPageComponent
  },
  {
    path: 'login', component: LoginPageComponent
  }
]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

