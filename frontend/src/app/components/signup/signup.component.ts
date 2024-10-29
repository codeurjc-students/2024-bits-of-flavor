import { Component } from "@angular/core";
import { UserService } from "../../service/user.service";
import { User } from "../../model/user.model";
import { LoginService } from "../../service/login.service";

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrl: './signup.component.css'
})
export class SignupComponent {

    user: User = new User();

    constructor(private loginService: LoginService){}
    
    public submitSignupForm(){
        if (this.user.username != "" && this.user.email != '' && this.user.encodedPassword != null){
        }
    }
}