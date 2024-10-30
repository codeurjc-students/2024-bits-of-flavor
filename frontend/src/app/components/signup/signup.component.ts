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
    imageSrc: String = "assets/images/default-profile-picture.jpg";

    constructor(private userService: UserService){}
    
    public submitSignupForm(){
        if (this.user.username != "" && this.user.email != '' && this.user.encodedPassword != null){
            this.userService.addUser(this.user).subscribe(
                (user: User) => console.log(user)
            );
        }
    }

    public modifyImageFile(event: any){
        const reader = new FileReader();
        reader.onload = (event: any) => {
          this.imageSrc = event.target.result;
        };
        reader.readAsDataURL(event.target.files[0]);
    }
}