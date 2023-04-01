import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Character, UserComment } from 'src/models';

@Injectable({
  providedIn: 'root'
})
export class MarvelService {

  private URL_CHARACTERS = "/api/characters";
  private URL_CHARACTER_ID = (id: string) => `/api/character/${id}`;


  constructor( private httpClient: HttpClient ) {}

  getCharacters(name: string, limit: number, offset: number) {

    let params = new HttpParams()
                    .append("name", name)
                    .append("limit", limit)
                    .append("offset", offset)

    return this.httpClient.get<{characters:Character[]}>(this.URL_CHARACTERS, {params});
  }

  getDetail(id: string) {
    return this.httpClient.get<{details:Character, comments:UserComment[]}>(this.URL_CHARACTER_ID(id));
  }
  
  postComment(id: string, payload: {comment: string}) {
    return this.httpClient.post(this.URL_CHARACTER_ID(id), payload);
  }
}
