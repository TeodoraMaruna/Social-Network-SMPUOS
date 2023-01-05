import {Comment} from "./comment";
import {Like} from "./like";
import {User} from "./user";

export class Post {
  Id: string = '';

  description: string = '';

  comments: Comment[] = new Array()

  likes: Like[] = new Array()

  user: User = new User();

  imageUrl: string = '';

  imageBase64: string = '';

  numberOfLikes: number = 0;
}
