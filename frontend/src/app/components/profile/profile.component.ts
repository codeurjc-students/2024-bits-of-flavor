import { Component, OnInit, ViewChild } from "@angular/core";
import { User } from "../../model/user.model";
import { UserService } from "../../service/user.service";
import { LoginService } from "../../service/login.service";
import { Ticket } from "../../model/ticket.model";
import { TicketService } from "../../service/ticket.service";
import { Product } from "../../model/product.model";

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrl: './profile.component.css'
  })

  export class ProfileComponent implements OnInit{

    user: User = new User();
    product: Product = new Product();
    imageSrc: String = "assets/images/default-profile-picture.jpg"
    editAttrUsername: boolean = false;
    editAttrEmail: boolean = false;
    editAttrPhoneNumber: boolean = false;

    tickets: Ticket[] = [];

    @ViewChild("file")
    file: any;

    constructor(private loginService: LoginService, private userService: UserService, private ticketService: TicketService){}

    ngOnInit() {
        this.loadCurrentUser();
    }

    public loadCurrentUser(){
        this.user = this.loginService.getUser();
        this.imageSrc = "/api/user/" + this.user.id + "/image";
        this.ticketService.getTicketsFromUser().subscribe(
            (tickets: Ticket[]) => this.tickets = tickets
        );
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
        this.editAttrPhoneNumber = false;
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

    public modifyImageFile(event: any){
        const reader = new FileReader();
        reader.onload = (event: any) => {
          this.imageSrc = event.target.result;
        };
        reader.readAsDataURL(event.target.files[0]);
        this.updateImage();
    }
  }