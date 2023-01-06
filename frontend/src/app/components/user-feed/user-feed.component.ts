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
import { UserConnection } from 'src/app/model/user-connection';
import { ConnectionService } from 'src/app/service/connection.service';
import { CreateConnection } from 'src/app/model/create-connection';

@Component({
  selector: 'app-user-feed',
  templateUrl: './user-feed.component.html',
  styleUrls: ['./user-feed.component.css']
})
export class UserFeedComponent implements OnInit {

  constructor(public dialog: MatDialog, private postService: PostService, private authService: AuthService, 
    private userService: UserService, private connectionService: ConnectionService, private router: Router) {
  }

  user: User = new User; // current user
  posts: Post[] = [];
  feed: Post[] = [];

  feedActive: boolean = true;
  profileActive: boolean = false;
  followerRequestsActive: boolean = false;
  followersActive: boolean = false;
  blockedActive: boolean = false;

  visibleUserAcccountSettings: boolean = false; 
  visible: boolean = false;                     
  loaded: Boolean = false;
  loadedFeed: Boolean = false;

  recommended: User[]  = []
  followers: UserConnection[] = []
  followRequests: UserConnection[] = []
  blocked: UserConnection[] = []
  sentFollowRequests: UserConnection[] = []

  ngOnInit(): void {
    this.feedActive = true;
    this.profileActive = false;
    this.followersActive = false;
    this.followerRequestsActive = false;
    this.blockedActive = false;
    this.user.username = this.authService.getUsername()

    this.loadUserInfo();
    this.loadFeed();
    this.getRecommendation();
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
    if (this.user.username != undefined){
      this.connectionService.findRecommended(this.user.username).subscribe(
        (data: any) => {
          this.recommended=[]
          this.recommended=data
          this.loaded = true;
        })
    }
  }

  follow(receiverUsername: String){
    let connection = new CreateConnection();
    connection.senderUsername = this.user.username;
    connection.receiverUsername = receiverUsername;
    this.connectionService.createConnection(connection).subscribe()

    alert("Successful follow or follow request!")
    this.getRecommendation();
    this.loadFeed()
    this.loadFollowers()
    this.loadFollowRequests()
    window.location.reload()
  }

  loadFeed(){
    this.feedActive = false;    
    this.profileActive = false;
    this.followersActive = false;    
    this.followerRequestsActive = false;
    this.blockedActive = false;

    if (this.user.username != undefined){
      this.connectionService.findPostsFromFollowers(this.user.username).subscribe(
        (data: any[]) => {
          this.feed = []
          this.feed = data
          this.feedActive = true;    
          this.loadedFeed = true;
        })
    }
  }

  loadMyPosts(){
    this.feedActive = false;
    this.profileActive = true;
    this.followersActive = false;    
    this.followerRequestsActive = false;
    this.blockedActive = false;

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
    this.blockedActive = false;

    if (this.user.username != undefined){
      this.connectionService.findFollowersForUsername(this.user.username).subscribe(
        (data: any) => {
          this.followers=data
        })
    }
  }

  loadBlocked(){
    this.feedActive = false;
    this.profileActive = false;
    this.followersActive = false;    
    this.followerRequestsActive = false;
    this.blockedActive = true;

    this.connectionService.findBlockedForUsername(this.user.username).subscribe(
      (data: any) => {
        this.blocked=data
      })
  }

  blockUser(receiverUsername: String){
    let connection = new CreateConnection();
    connection.receiverUsername = receiverUsername;
    connection.senderUsername = this.user.username;
    this.connectionService.blockUser(connection).subscribe()

    alert("User successfully blocked!")
    this.getRecommendation()
    this.loadFeed()
    this.loadFollowers()
    this.loadFollowRequests()
    this.loadBlocked()
    window.location.reload()
  }

  unblockUser(receiverUsername: String){
    let connection = new CreateConnection();
    connection.receiverUsername = receiverUsername;
    connection.senderUsername = this.user.username;
    this.connectionService.removeBlocked(connection).subscribe()
    this.connectionService.removeBlockedBy(connection).subscribe()

    alert("User successfully unblocked!")
    this.getRecommendation()
    this.loadFeed()
    this.loadFollowers()
    this.loadFollowRequests()
    this.loadBlocked()
    window.location.reload()
  }

  approveFollowRequest(senderUsername: String){
    let connection = new CreateConnection();
    connection.senderUsername = senderUsername;
    connection.receiverUsername = this.user.username;
    this.connectionService.approveFollowRequest(connection).subscribe()

    alert("Follow request approved!")
    this.getRecommendation()
    this.loadFeed()
    this.loadFollowers()
    this.loadFollowRequests()
    window.location.reload()
  }

  rejectFollowRequest(senderUsername: String){
    let connection = new CreateConnection();
    connection.senderUsername = senderUsername;
    connection.receiverUsername = this.user.username;
    this.connectionService.rejectFollowRequest(connection).subscribe()

    alert("Follow request rejected!")
    this.getRecommendation()
    this.loadFeed()
    this.loadFollowers()
    this.loadFollowRequests()
    window.location.reload()
  }

  loadFollowRequests(){
    this.feedActive = false;
    this.profileActive = false;
    this.followersActive = false;    
    this.followerRequestsActive = true;
    this.blockedActive = false;

    this.connectionService.findFollowRequestsForUsername(this.user.username).subscribe(
      (data: any) => {
        this.followRequests=data
      })

       
    this.connectionService.findSentFollowRequestsForUsername(this.user.username).subscribe(
      (data: any) => {
        this.sentFollowRequests=data
      }) 
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

  seeProfile(username: String){
    if (username != undefined){
      this.router.navigate(['/user-profile', username]);
    }
  }

  editInfo() {
    const dialogRef = this.dialog.open(UpdateInfoComponent, {
      width: '43vw',
      data: {},
    });

    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
      alert("User info saved!")
    });
  }

}
