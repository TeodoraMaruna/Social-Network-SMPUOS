import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {Post} from "../../model/post";
import {MatDialog} from "@angular/material/dialog";
import {NewPostComponent} from "../dialogs/new-post/new-post.component";
import {UpdateInfoComponent} from "../dialogs/update-info/update-info.component";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-user-feed',
  templateUrl: './user-feed.component.html',
  styleUrls: ['./user-feed.component.css']
})
export class UserFeedComponent implements OnInit {



  constructor(public dialog: MatDialog ) {

  }

  user: User = new User; // current user
  posts: Post[] = [];
  searchedPosts: Post[] = [];
  loaded: boolean = false;
  feedActive: boolean = true;
  profileActive: boolean = false;
  informationsActive: boolean = false;
  recommendationActive: boolean = false;

  visibleUserAcccountSettings: boolean = false;
  searchCriteria: string = "";
  stringBirthday : any;
  visible: boolean = false;
  users: User[]  = []
  jobOffers: Post[] = []
  ngOnInit(): void {
    this.feedActive = true;
    this.profileActive = false;
    this.informationsActive = false;
    this.recommendationActive = false;
    this.loadUserData();

    this.getRecommendation()


    //this.loadJobRecommendations()
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

  getUsersFromRecommendation(){
    // this.users = []
    //
    // for(let u of data){
    //   this.userService.getById(u.userID).subscribe((data: User) => {
    //     this.users.push(data['user'])
    //   })
    // }
    //
    // console.log("preporuke:")
    // console.log(this.users)

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

  loadUserData(){
  }

  loadFeed(){

  }

  loadMyPosts(){
    // this.feedActive = false;
    // this.profileActive = true;
    // this.informationsActive = false;
    // this.recommendationActive = false;
    // let userId = localStorage.getItem("user");
    //
    // if (userId != undefined){
    //   this.postService.getByUserId(userId).subscribe(
    //     (data: any[]) => {
    //       let posts = data['posts']
    //       console.log(posts)
    //
    //       for (let p of posts){
    //         let dateTime = p.dateCreated.split('T')
    //         let time = dateTime[1].split('.')
    //         p.dateCreated = dateTime[0] + '  ' + time[0]
    //
    //         this.userService.getById(p.userId).subscribe(
    //           (user: any) => {
    //             p.user = user
    //           })
    //
    //         for (let c of p.comments){
    //           this.userService.getById(c.userId).subscribe(
    //             (user: any) => {
    //               c.user = user
    //             })
    //         }
    //
    //         //if (p.isJobOffer)
    //       }
    //
    //       this.posts = []
    //       this.posts = posts
    //       this.searchedPosts = this.posts
    //       console.dir(this.posts)
    //     })
    // }
  }


  makeVisibleUserAcccountSettings() {
    this.visibleUserAcccountSettings = !this.visibleUserAcccountSettings
  }

  comment(postId: string, event: any){
    // var sanitize = require("mongo-sanitize"); // mongo-sanitize module
    // // The sanitize function will strip out any keys that start with '$' in the input,
    // // so you can pass it to MongoDB without worrying about malicious users overwriting
    // // query selectors.
    //
    // let dto = new CommentDto();
    // dto.postId = postId;
    // dto.text = sanitize(event.target.comment.value);
    //
    // if (dto.text != "" && dto.text.trim() != "" && dto.text != undefined){
    //   this.postService.commentPost(dto).subscribe(
    //     (data: SuccessMessage) => {
    //       if (this.feedActive){
    //         this.loadFeed();
    //       } else if(this.profileActive){
    //         this.loadMyPosts();
    //       }
    //       else if(this.recommendationActive){
    //         this.loadJobRecommendations();
    //       }
    //     }
    //   )
    // } else {
    //   alert('Comment can not be empty!')
    // }
  }

  like(postId: string){
    // let dto = new PostDto();
    // dto.postId = postId;
    // this.postService.likePost(dto).subscribe(
    //   (data: SuccessMessage) => {
    //     if (this.feedActive){
    //       this.loadFeed();
    //     } else if(this.profileActive){
    //       this.loadMyPosts();
    //     }
    //     else if(this.recommendationActive){
    //       this.loadJobRecommendations();
    //     }
    //   }
    // )
  }




  seeLikes(postId: string){
    // const dialogRef = this.dialog.open(PostLikesComponent, {
    //   width: '400px',
    //   height: '230px',
    //   data: {},
    // });
    // dialogRef.componentInstance.post.id = postId;
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

  search(){
    // this.posts = this.searchedPosts.filter(p =>
    //   (p.text).toLowerCase().includes(this.searchCriteria.toLowerCase()) ||
    //   (p.dateCreated).toLowerCase().includes(this.searchCriteria.toLowerCase())
    // )
  }

  searchFeed(){
    // this.feedActive = true;
    // this.profileActive = false;
    // this.recommendationActive = false;
    // this.posts = this.searchedPosts.filter(p =>
    //   (p.text).toLowerCase().includes(this.searchCriteria.toLowerCase()) ||
    //   (p.dateCreated).toLowerCase().includes(this.searchCriteria.toLowerCase())
    // )
  }

  searchProfile(){
    // this.feedActive = false;
    // this.profileActive = true;
    // this.posts = this.searchedPosts.filter(p =>
    //   (p.text).toLowerCase().includes(this.searchCriteria.toLowerCase()) ||
    //   (p.dateCreated).toLowerCase().includes(this.searchCriteria.toLowerCase())
    // )
  }

  seeProfile(id: string){
    // let userId =  localStorage.getItem("user");
    //
    // if (id != userId){
    //   this.router.navigate(['profile', id])
    // }
  }

  loadMyInfo(){
    this.feedActive = false;
    this.profileActive = false;
    this.recommendationActive = false;
    this.informationsActive = true;
  }



  updateUser(){
  }

  generateAPIToken(){
  }

  changeVisibility(){
  }

  editInfo() {
    const dialogRef = this.dialog.open(UpdateInfoComponent, {
      width: '40vw',
      data: {},
    });

    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
    });
  }
}
