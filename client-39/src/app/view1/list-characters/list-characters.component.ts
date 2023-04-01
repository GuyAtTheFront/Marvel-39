import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { MarvelService } from 'src/app/services/marvel.service';
import { Character } from 'src/models';

@Component({
  selector: 'app-list-characters',
  templateUrl: './list-characters.component.html',
  styleUrls: ['./list-characters.component.css']
})
export class ListCharactersComponent implements OnInit {

  characters!: Character[]
  private page = 0;
  private limit = 20;
  private offset = 0;
  private name = "";
  isPreviousDisabled = true;
  isNextDisabled = false;

  constructor ( private router: Router,
                private marvelService: MarvelService, 
                private activatedRoute: ActivatedRoute) {}
  
  

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(x => {this.name = x['name'] });
    this.marvelService.getCharacters(this.name, this.limit, this.offset + (this.page * this.limit))
                        .subscribe(x=> {this.characters = x.characters});
  }


  onPrevious() {
    console.log("previous");
    this.page = Math.max(0, this.page - 1)

    if (this.page <= 0 && !this.isPreviousDisabled) {
      this.isPreviousDisabled = true;
    }

    if(this.isNextDisabled) {
      this.isNextDisabled = false;
    }

    this.marvelService.getCharacters(this.name, this.limit, this.offset + (this.page * this.limit))
                        .subscribe(x=> {this.characters = x.characters});
  }

  onNext() {
    console.log("next");
    this.page++;

    if (this.isPreviousDisabled) {
      this.isPreviousDisabled = false;
    }

    if (this.characters.length < this.limit && !this.isNextDisabled) {
      this.isNextDisabled = true;
    }

    this.marvelService.getCharacters(this.name, this.limit, this.offset + (this.page * this.limit))
                        .subscribe(x=> {this.characters = x.characters});
  }

  onSelectHero(id: String) {
    console.log(id);
    this.router.navigate(["/list", id]);
  }
}
