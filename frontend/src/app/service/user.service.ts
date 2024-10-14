import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { User } from "../model/user.model";

const API_URL = "/api/"

@Injectable({
    providedIn: 'root'
})

export class UserService {

    constructor(private httpClient: HttpClient){}
}