import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Like } from 'src/app/model/like';
import { Post } from 'src/app/model/post';
import { PostService } from 'src/app/service/post.service';

@Component({
  selector: 'app-post-likes',
  templateUrl: './post-likes.component.html',
  styleUrls: ['./post-likes.component.css']
})
export class PostLikesComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<PostLikesComponent>, private postService: PostService) { }

  post: Post = new Post;
  likedBy: Like[] = [];

  ngOnInit(): void {
    for(let like of this.post.likes){
      this.likedBy.push(like);
    }
  }

}
