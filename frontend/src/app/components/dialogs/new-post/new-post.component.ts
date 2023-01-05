import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {
  post: any;
  invalidLink: any;
  link: any
  image: any;
  url: any;
  uploadedImage: string | ArrayBuffer = ''

  constructor(public dialogRef: MatDialogRef<NewPostComponent>) { }

  ngOnInit(): void {
  }

  uploadImage(e: any) {
    this.url = e.target.result;
    console.log(this.url)
    const file = e.target.files[0];
    this.createBase64Image(file);
  }

  clearImage() {

  }

  onNoClick() {

  }

  save() {

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
