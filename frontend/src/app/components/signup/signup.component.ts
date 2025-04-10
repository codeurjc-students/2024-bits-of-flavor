import { Component, ViewChild } from "@angular/core";
import { UserService } from "../../service/user.service";
import { User } from "../../model/user.model";
import { Router } from "@angular/router";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrl: './signup.component.css'
})
export class SignupComponent {

    user: User = new User();
    imageSrc: String = "assets/images/default-profile-picture.jpg";
    confirmPass: String = "";

    @ViewChild("file")
    file: any;

    constructor(private userService: UserService, private router: Router) { }

    public submitSignupForm() {
        if (this.user.encodedPassword != this.confirmPass) {
            alert("ERROR: Las contraseñas no coinciden");
            return;
        }
        if (this.user.username != "" && this.user.email != '' && this.user.encodedPassword != null) {
            this.userService.addUser(this.user).subscribe({
                next: (user: User) => {
                    this.updateImage(user);
                    this.router.navigate(["/"]);
                },
                error: (e: HttpErrorResponse) => {
                    console.log(e);
                    alert("ERROR: NO se ha podido registrar cuenta de usuario");
                }
            });
        } else {
            alert("ERROR: Faltan campos por rellenar");
        }
    }

    public updateImage(user: User) {
        const image = this.file.nativeElement.files[0];
        if (image) {
            const data = new FormData();
            data.append('imageFile', image);
            this.userService.setUserImage(user, data).subscribe();
        }
    }

    public modifyImageFile(event: any) {
        const reader = new FileReader();
        reader.onload = (event: any) => {
            this.imageSrc = event.target.result;
        };
        reader.readAsDataURL(event.target.files[0]);
    }
}