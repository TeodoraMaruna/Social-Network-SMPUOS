import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import { Like } from '../model/like';
import { Comment } from '../model/comment';
import { Post } from '../model/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private readonly userPath = '/post-service';

  constructor(private _http: HttpClient) {
  }

  // TODO: delete ?
  public saveComment(): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}`)
  }

  public likePost(postId: String, like: Like): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/add-like/` + postId, like)
  }

  public commentPost(postId: String, comment: Comment): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/add-comment/` + postId, comment)
  }

  public getPostsByUsername(username: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/find-all-posts/user/` + username)
  }

  public getPostsById(id: String): Observable<any> {
    return this._http.get<any>(environment.apiUrl + `${this.userPath}/find-post/` + id)
  }

  public newPost(post: Post): Observable<any> {
    return this._http.post<any>(environment.apiUrl + `${this.userPath}/add-post`, post)
  }

}
