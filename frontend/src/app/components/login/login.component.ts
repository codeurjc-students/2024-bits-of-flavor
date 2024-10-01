import { Component } from "@angular/core";
import { UserService } from "../../service/user.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
  })

  export class LoginComponent {
    username: string = "";
    password: string = "";

    constructor(private userService: UserService){}

    public submitLoginForm(){

    }
  }