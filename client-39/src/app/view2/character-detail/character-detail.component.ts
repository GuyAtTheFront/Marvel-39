import { Component, Input } from '@angular/core';
import { Character } from 'src/models';

@Component({
  selector: 'app-character-detail',
  templateUrl: './character-detail.component.html',
  styleUrls: ['./character-detail.component.css']
})
export class CharacterDetailComponent {

  @Input()
  detail!: Character;

}
