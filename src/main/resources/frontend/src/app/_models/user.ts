export class User{

    constructor(
        public email : string,
        public firstName : string,
        public lastName : string,
        public password? : string,
        public username? : string,
        public _id?: string,
        public avatarPath? : string,
        public avatarPathThumbnail? : string
       
  ) {  }

}