import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-character',
  templateUrl: './search-character.component.html',
  styleUrls: ['./search-character.component.css']
})

export class SearchCharacterComponent implements OnInit {

  characterForm!: FormGroup;

  constructor( private fb: FormBuilder, 
              private router: Router ) {}

  ngOnInit(): void {
    this.characterForm = this.createForm();
  }

  createForm() : FormGroup {
    let grp = this.fb.group({
      name: this.fb.control<string>("", Validators.required)
    })
    return grp;
  }

  processForm() {
    console.log(this.characterForm.value);
    this.router.navigate(["/list"], {queryParams: {name: this.characterForm.get("name")?.value}});
  }

}
