import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchCharacterComponent } from './view0/search-character/search-character.component';
import { ListCharactersComponent } from './view1/list-characters/list-characters.component';
import { ContainerTwoComponent } from './view2/container-two/container-two.component';
import { FormCommentsComponent } from './view3/form-comments/form-comments.component';

const routes: Routes = [ 
  { path: "", component: SearchCharacterComponent },
  { path: "list", component: ListCharactersComponent },
  { path: "list/:id", component: ContainerTwoComponent },
  { path: "list/:id/comment", component: FormCommentsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
