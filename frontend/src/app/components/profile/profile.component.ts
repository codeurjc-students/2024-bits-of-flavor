import { Component, OnInit, ViewChild } from "@angular/core";
import { User } from "../../model/user.model";
import { UserService } from "../../service/user.service";
import { LoginService } from "../../service/login.service";

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrl: './profile.component.css'
  })

  export class ProfileComponent implements OnInit{

    user: User = new User();
    editAttrUsername: boolean = false;
    editAttrEmail: boolean = false;
    editAttrPhoneNumber: boolean = false;

    @ViewChild("file")
    file: any;

    constructor(private loginService: LoginService, private userService: UserService){}

    ngOnInit() {
        this.loadCurrentUser();
    }

    public loadCurrentUser(){
        this.user = this.loginService.getUser();
    }

    public editUsername(){
        this.editAttrUsername = true;
    }

    public editEmail(){
        this.editAttrEmail = true;
    }

    public editPhoneNumber(){
        this.editAttrPhoneNumber = true;
    }

    public confirmUsername(){
        this.updateUser();
        this.editAttrUsername = false;
    }

    public confirmEmail(){
        this.updateUser();
        this.editAttrEmail = false;
    }

    public confirmPhoneNumber(){
        this.updateUser();
        this.editAttrPhoneNumber = true;
    }

    public updateUser(){
        this.userService.updateUser(this.user).subscribe(
            () => this.loadCurrentUser()
        );
    }

    public updateImage(){
        const image = this.file.nativeElement.files[0];
        if (image) {
          const data = new FormData();
          data.append('imageFile', image);
          this.userService.setUserImage(this.user, data).subscribe(
            () => this.loadCurrentUser()
          );
        }
    }
  }