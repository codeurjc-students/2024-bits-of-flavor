import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { User } from '../model/user.model';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

const BASE_URL = '/api';

@Injectable({ providedIn: 'root' })
export class LoginService {

    logged: boolean = false;
    user: User = new User();

    constructor(private http: HttpClient, private router: Router) {
        this.reqIsLogged();
    }

    reqIsLogged() {

        this.http.get(BASE_URL + "/user/me", { withCredentials: true} ).subscribe({
            next: (v) => {
                this.logged = true
                this.user = v as User
                console.log(this.user)
            },
            error: (e: HttpErrorResponse) => {
                if (e.status == 401) {
                    this.logged = false
                }
                console.error(e)
            }
        })

    }

    logIn(username: string, password: string) {

        this.http.post(BASE_URL + "/login", { "username": username, "password": password }, { withCredentials: true })
            .subscribe({
                next: () => {
                    this.reqIsLogged();
                    this.router.navigate(['/']);
                },
                error: (e: HttpErrorResponse) => {
                    alert("Credenciales inválidas.");
                }
        })
    }

    logOut() {

        return this.http.post(BASE_URL + '/logout', { withCredentials: true })
            .subscribe((resp: any) => {
                console.log("LOGOUT: Successfully");
                this.logged = false;
                this.user = new User();
            });

    }

    isLogged() {
        return this.logged;
    }

    getUser() {
        return this.user;
    }

    isAdmin() {
        return this.user && this.user.roles.indexOf('ADMIN') !== -1;
    }

    getCurrentUser(): Observable<User> {
        console.log(this.user);
        return this.http.get("api/user/me", { withCredentials: true}) as Observable<User>;
    }
}