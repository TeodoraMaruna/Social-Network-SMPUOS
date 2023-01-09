import {Comment} from "./comment";
import {Like} from "./like";
import {User} from "./user";

export class Post {
  id: String = '';

  description: String = '';

  comments: Comment[] = new Array()

  likes: Like[] = new Array()

  user: User = new User();

  imageUrl: String = '';

  imageBase64: string | ArrayBuffer = '';

  numberOfLikes: number = 0;
}
