import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { User } from "../model/user.model";

const API_URL = "/api/user/"

@Injectable({
    providedIn: 'root'
})

export class UserService {
    login (username: string, password: string) {
        this.httpClient.post(API_URL + "/login", {"username": username, "password": password});
    }

    constructor(private httpClient: HttpClient){}
}