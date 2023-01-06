import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from 'src/app/model/comment';
import { CreateConnection } from 'src/app/model/create-connection';
import { Like } from 'src/app/model/like';
import { Post } from 'src/app/model/post';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';
import { ConnectionService } from 'src/app/service/connection.service';
import { PostService } from 'src/app/service/post.service';
import { UserService } from 'src/app/service/user.service';
import { PostLikesComponent } from '../dialogs/post-likes/post-likes.component';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  constructor(private route: ActivatedRoute, private authService: AuthService, private userService: UserService,
    private postService: PostService, private connectionService: ConnectionService, public dialog: MatDialog,
    private router: Router) { }

  username: String = "";
  user: User = new User();
  posts: Post[] = []
  followEachOther: boolean = false;
  followRequestSent: boolean = false;
  allowedAccess: boolean = false;
  showMessage: boolean = false;
  loaded1: boolean = false;
  loaded2: boolean = false;

  ngOnInit(): void {
    this.username = this.route.snapshot.paramMap.get('username')!;
    this.loadUser();
    this.checkIfUsersFollowEachOther();
    this.checkIfUserSentFollowRequest();
  }

  loadUser(){
    if (this.username != undefined){
      this.userService.getUserByUsername(this.username).subscribe(
        (data: any) => {
          this.user=data
        })

      this.postService.getPostsByUsername(this.username).subscribe(
        (data: any[]) => {
          this.posts = []
          this.posts = data
        })  
    }
  }

  comment(post: Post, event: any){
    let postComment = new Comment();
    postComment.content = event.target.comment.value
    postComment.username = this.authService.getUsername()
    this.postService.commentPost(post.id, postComment).subscribe()

    window.location.reload()
  }

  like(post: Post){
    let like = new Like();
    like.username = this.authService.getUsername()
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

  block(receiverUsername: String){
    this.loaded1 = false;
    this.loaded2 = false;

    let connection = new CreateConnection();
    connection.receiverUsername = receiverUsername;
    connection.senderUsername = this.authService.getUsername();
    this.connectionService.blockUser(connection).subscribe()

    alert("User successfully blocked!")
    this.router.navigate(['/user-feed']);
  }

  follow(receiverUsername: String){
    this.loaded1 = false;
    this.loaded2 = false;

    let connection = new CreateConnection();
    connection.senderUsername = this.authService.getUsername();
    connection.receiverUsername = receiverUsername;
    this.connectionService.createConnection(connection).subscribe()


    alert("Sucessfull follow or follow request");
    this.checkIfUsersFollowEachOther();
    this.checkIfUserSentFollowRequest();
  }

  checkIfUsersFollowEachOther(){
    let connection = new CreateConnection()
    connection.receiverUsername = this.username
    connection.senderUsername = this.authService.getUsername()
    this.connectionService.checkIfUsersFollowEachOther(connection).subscribe(
      (data: any) => {
        this.followEachOther=data
        console.log("follow each other", this.followEachOther)

        if(this.followEachOther || this.user.isPublic){
          this.allowedAccess = true;
          this.showMessage = false;
        } else {
          this.allowedAccess = false;
          this.showMessage = true;
        }
        this.loaded1 = true;
      }
    )
  }

  // ne moze mu poslati request ako je vec zapratio - pisace follow request sent
  checkIfUserSentFollowRequest(){
    let connection = new CreateConnection()
    connection.receiverUsername = this.username
    connection.senderUsername = this.authService.getUsername()
    this.connectionService.checkIfUserSentFollowRequest(connection).subscribe(
      (data: any) => {
        this.followRequestSent=data
        console.log("follow request sent", this.followRequestSent)
        this.loaded2 = true;
      }
    )
  }

}
