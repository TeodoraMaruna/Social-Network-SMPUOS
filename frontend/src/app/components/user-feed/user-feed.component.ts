import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {Post} from "../../model/post";
import {MatDialog} from "@angular/material/dialog";
import {NewPostComponent} from "../dialogs/new-post/new-post.component";
import {UpdateInfoComponent} from "../dialogs/update-info/update-info.component";
import {FormBuilder, FormGroup} from "@angular/forms";
import { PostService } from 'src/app/service/post.service';
import { PostLikesComponent } from '../dialogs/post-likes/post-likes.component';
import { Like } from 'src/app/model/like';
import { Comment } from 'src/app/model/comment';

@Component({
  selector: 'app-user-feed',
  templateUrl: './user-feed.component.html',
  styleUrls: ['./user-feed.component.css']
})
export class UserFeedComponent implements OnInit {

  constructor(public dialog: MatDialog, private postService: PostService) {
  }

  user: User = new User; // current user
  posts: Post[] = [];
  loaded: boolean = false;

  feedActive: boolean = true;
  profileActive: boolean = false;
  followerRequestsActive: boolean = false;
  followersActive: boolean = false;

  visibleUserAcccountSettings: boolean = false;
  stringBirthday : any;
  visible: boolean = false;
  users: User[]  = []

  ngOnInit(): void {
    this.feedActive = true;
    this.profileActive = false;
    this.followersActive = false;
    this.followerRequestsActive = false;
    this.getRecommendation()
  }


  getRecommendation(){
    // let userId =  localStorage.getItem("user");
    //
    // this.connectionService.getRecommendation(userId).subscribe(
    //   (data) => {
    //     console.log(data)
    //     this.getUsersFromRecommendation(data['users'])
    //   })

  }

  follow(){
    // var followDTO = {
    //   "userID": user.id,
    //   "isPublic": user.isPublic,
    //   "isPublicLogged": false
    // }
    //
    // this.connectionService.connect(followDTO).subscribe((res: any) => {
    //   window.location.reload()
    // })
  }

  loadFeed(){
    this.feedActive = true;    
    this.profileActive = false;
    this.followersActive = false;    
    this.followerRequestsActive = false;
  }

  loadMyPosts(){
    this.feedActive = false;
    this.profileActive = true;
    this.followersActive = false;    
    this.followerRequestsActive = false;
    // let userId = localStorage.getItem("user");
    let userId = 1 // TODO: change

    if (userId != undefined){
      this.postService.getPostsByUserId(userId).subscribe(
        (data: any[]) => {
          this.posts = []
          this.posts = data
        })
    }
  }

  loadFollowers(){
    this.feedActive = false;
    this.profileActive = false;
    this.followersActive = true;    
    this.followerRequestsActive = false;
  }

  loadFollowRequests(){
    this.feedActive = false;
    this.profileActive = false;
    this.followersActive = false;    
    this.followerRequestsActive = true;
  }

  makeVisibleUserAcccountSettings() {
    this.visibleUserAcccountSettings = !this.visibleUserAcccountSettings
  }

  comment(post: Post, event: any){
    let postComment = new Comment();
    postComment.content = event.target.comment.value
    postComment.username = "jana"
    this.postService.commentPost(post.id, postComment).subscribe()

    window.location.reload()
    // if(this.feedActive){
    //   this.loadFeed();
    // } else {
    //   this.loadMyPosts();
    // }
  }

  like(post: Post){
    let like = new Like();
    like.username = "tea";  // TODO: change - uzeti ulogovanog korisnika
    this.postService.likePost(post.id, like).subscribe()
    
    window.location.reload()
  }


  seeLikes(post: Post){
    const dialogRef = this.dialog.open(PostLikesComponent, {
      width: '400px',
      height: '230px',
      data: {},
    });
    dialogRef.componentInstance.post = post;
  }

  newPost(){
    const dialogRef = this.dialog.open(NewPostComponent, {
      width: '40vw',
      data: {},
    });

    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
    });
  }

  logout(){
    // this.authService.logout();
    // this.router.navigate(['']);
  }

  seeProfile(id: string){
    // let userId =  localStorage.getItem("user");
    //
    // if (id != userId){
    //   this.router.navigate(['profile', id])
    // }
  }

  changeVisibility(){
  }

  editInfo() {
    const dialogRef = this.dialog.open(UpdateInfoComponent, {
      width: '45vw',
      data: {},
    });

    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
    });
  }
}
