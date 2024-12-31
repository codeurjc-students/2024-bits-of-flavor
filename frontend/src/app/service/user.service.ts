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

    addUser(user: User): Observable<User> {
        return this.httpClient.post(BASE_URL, user) as Observable<User>;
    }

    updateUser(user: User): Observable<User> {
        return this.httpClient.put(BASE_URL + user.id, user) as Observable<User>
    }

    setUserImage(user: User, data: FormData): Observable<User> {
        return this.httpClient.put(BASE_URL + user.id + "/image", data) as Observable<User>
    }
}