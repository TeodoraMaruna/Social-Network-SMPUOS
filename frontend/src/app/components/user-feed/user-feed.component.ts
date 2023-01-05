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
import { AuthService } from 'src/app/service/auth.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-feed',
  templateUrl: './user-feed.component.html',
  styleUrls: ['./user-feed.component.css']
})
export class UserFeedComponent implements OnInit {

  constructor(public dialog: MatDialog, private postService: PostService, private authService: AuthService, private userService: UserService,
    private router: Router) {
  }

  user: User = new User; // current user
  posts: Post[] = [];
  loaded: boolean = false;
  username: String = "";

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
    this.user.username = this.authService.getUsername()

    this.loadUserInfo();
    this.getRecommendation()
  }

  loadUserInfo(){
    if (this.user.username != undefined){
      this.userService.getUserByUsername(this.user.username).subscribe(
        (data: any) => {
          this.user=data
        })
    }
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

    if (this.user.username != undefined){
      this.postService.getPostsByUsername(this.user.username).subscribe(
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
    postComment.username = this.user.username
    this.postService.commentPost(post.id, postComment).subscribe()

    window.location.reload()
  }

  like(post: Post){
    let like = new Like();
    like.username = this.user.username
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
    this.authService.logout();
    this.router.navigate(['']);
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
      width: '43vw',
      data: {},
    });

    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
    });
  }
}
