import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserComment } from 'src/models';

@Component({
  selector: 'app-character-comments',
  templateUrl: './character-comments.component.html',
  styleUrls: ['./character-comments.component.css']
})
export class CharacterCommentsComponent {

  @Input()
  comments!: UserComment[];

  @Input()
  id!: string;

  @Output()
  commentClicked = new EventEmitter();

  constructor( private router: Router,
              private activatedRoute: ActivatedRoute ) {}

  onClick() {
    console.log("click");
    this.router.navigate(["comment"], {relativeTo: this.activatedRoute});
    this.commentClicked.emit();
  }
}
