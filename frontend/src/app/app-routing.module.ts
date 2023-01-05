import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./components/home-page/home-page.component";
import {PostPageComponent} from "./components/post-page/post-page.component";
import {UserFeedComponent} from "./components/user-feed/user-feed.component";

const routes: Routes = [
  {
    path: '', component: UserFeedComponent
  }]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

