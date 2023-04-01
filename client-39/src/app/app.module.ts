import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListCharactersComponent } from './view1/list-characters/list-characters.component';
import { ContainerTwoComponent } from './view2/container-two/container-two.component';
import { CharacterDetailComponent } from './view2/character-detail/character-detail.component';
import { CharacterCommentsComponent } from './view2/character-comments/character-comments.component';
import { FormCommentsComponent } from './view3/form-comments/form-comments.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SearchCharacterComponent } from './view0/search-character/search-character.component';
import { HttpClientModule } from '@angular/common/http'


@NgModule({
  declarations: [
    AppComponent,
    SearchCharacterComponent,
    ListCharactersComponent,
    ContainerTwoComponent,
    CharacterDetailComponent,
    CharacterCommentsComponent,
    FormCommentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
