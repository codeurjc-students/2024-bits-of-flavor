import { Component } from "@angular/core";
import { User } from "../../model/user.model";
import { LoginService } from "../../service/login.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
  })

  export class LoginComponent {
    user: User = new User();

    constructor(private loginService: LoginService){}

    public submitLoginForm(){
      this.loginService.logIn(this.user.username, this.user.password);
    }
  }