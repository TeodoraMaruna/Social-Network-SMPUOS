import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-update-info',
  templateUrl: './update-info.component.html',
  styleUrls: ['./update-info.component.css']
})
export class UpdateInfoComponent implements OnInit {
  user: any;
  stringBirthday: any;
  message: any;
  options: FormGroup;
  genderControl: any;

  constructor(public dialogRef: MatDialogRef<UpdateInfoComponent>, fb: FormBuilder) {
    this.options = fb.group({
      gender: this.genderControl
    });
  }

  ngOnInit(): void {
    this.genderControl = new FormControl('Female');
  }

  selectBirth() {

  }

  cancel() {
    this.dialogRef.close();
  }

  update() {

  }
}
