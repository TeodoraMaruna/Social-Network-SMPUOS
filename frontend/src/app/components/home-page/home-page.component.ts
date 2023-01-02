import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {PostService} from "../../service/post.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private _postService: PostService, private _router: Router) { }

  ngOnInit(): void {
  }

  getAllPosts() {
    this._postService.saveComment().subscribe(
      () => {
        console.log("200 OK")
      }
    )
  }
}
