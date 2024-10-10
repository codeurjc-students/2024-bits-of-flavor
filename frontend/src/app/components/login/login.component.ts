import { Component } from "@angular/core";
import { UserService } from "../../service/user.service";
import { User } from "../../model/user.model";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
  })

  export class LoginComponent {
    public user: User = new User();

    constructor(private userService: UserService){}

    public submitLoginForm(){
      this.userService.login(this.user).subscribe(
        (user: User) => this.user = user
      );
    }
  }