import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { User } from "../model/user.model";

const API_URL = "/api/"

@Injectable({
    providedIn: 'root'
})

export class UserService {
    login (user: User): Observable<User> {
        return this.httpClient.post(API_URL + "login", { "username": user.username, "password": user.encodedPassword }, { withCredentials: true }) as Observable<User>;
    }

    constructor(private httpClient: HttpClient){}
}