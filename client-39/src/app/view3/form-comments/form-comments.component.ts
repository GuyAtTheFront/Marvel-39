import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MarvelService } from 'src/app/services/marvel.service';

@Component({
  selector: 'app-form-comments',
  templateUrl: './form-comments.component.html',
  styleUrls: ['./form-comments.component.css']
})
export class FormCommentsComponent implements OnInit {

  commentForm!: FormGroup;
  name!: string;
  id!: string;

  constructor( private fb: FormBuilder,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private marvelService: MarvelService) {}

  ngOnInit(): void {
    this.commentForm = this.createForm();
    this.activatedRoute.queryParams.subscribe(x => {this.name = x['name']});
    this.activatedRoute.params.subscribe(x => {this.id = x['id']});
  }

  createForm() : FormGroup {
    return this.fb.group({ comment: this.fb.control<string>("")})
  }

  processForm() {
    console.log("processForm");
    // console.log(this.commentForm.value)
    this.marvelService.postComment(this.id, this.commentForm.value).subscribe( x => console.log(x));
    this.router.navigate([".."], {relativeTo: this.activatedRoute})
  }

}
