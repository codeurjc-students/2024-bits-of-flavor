import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { User } from "../model/user.model";

const BASE_URL = "/api/user/"

@Injectable({
    providedIn: 'root'
})

export class UserService {

    constructor(private httpClient: HttpClient){}

    updateUser(user: User): Observable<User> {
        return this.httpClient.put(BASE_URL + user.id, user) as Observable<User>
    }
}