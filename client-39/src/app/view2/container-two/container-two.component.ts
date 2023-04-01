import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MarvelService } from 'src/app/services/marvel.service';
import { Character, UserComment } from 'src/models';

@Component({
  selector: 'app-container-two',
  templateUrl: './container-two.component.html',
  styleUrls: ['./container-two.component.css']
})
export class ContainerTwoComponent implements OnInit {

  id!: string;
  comments!: UserComment[];
  detail!: Character;

  constructor( private activatedRoute: ActivatedRoute,
                private marvelService: MarvelService, 
                private router: Router) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(x => this.id = x["id"]);
    this.marvelService.getDetail(this.id).subscribe(x => {this.comments=x.comments; this.detail=x.details})
  }

  onCommentClicked() {
    this.router.navigate(["comment"], {relativeTo: this.activatedRoute, queryParams: {name: this.detail.name}});
  }

}
