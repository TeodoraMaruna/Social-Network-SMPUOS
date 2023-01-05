import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import { Post } from 'src/app/model/post';
import { PostService } from 'src/app/service/post.service';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<NewPostComponent>, private postService: PostService) { }

  post: Post = new Post();
  image: any;
  url: any;
  uploadedImage: string | ArrayBuffer = ''

  ngOnInit(): void {
  }

  clearImage() {
    
  }

  onNoClick() {
    this.dialogRef.close();
  }

  save() {
    this.post.user.username = "tea";  // TODO: change - uzeti ulogovanog korisnika
    // this.post.imageBase64 = this.uploadedImage
    this.postService.newPost(this.post).subscribe()
    this.dialogRef.close()
  }

  uploadImage(e: any) {
    this.url = e.target.result;
    console.log(this.url)
    const file = e.target.files[0];
    this.createBase64Image(file);
  }

  createBase64Image(file: File){
    const reader= new FileReader();
    reader.onload = (e) =>{
      if (!e.target?.result) return;
      let img = e.target.result;
      this.uploadedImage = img;
      console.log(this.uploadedImage)
    }
    reader.readAsDataURL(file);
  }
}
