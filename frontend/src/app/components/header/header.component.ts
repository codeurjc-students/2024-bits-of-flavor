import { Component } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { User } from '../../model/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  user: User = new User();
  logged: boolean = false;
  admin: boolean = false;

  constructor(public loginService: LoginService){}

  logout(){
    this.loginService.logOut();
  }
}
