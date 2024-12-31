import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Ticket } from "../model/ticket.model";

const BASE_URL = "/api/ticket/"

@Injectable({
    providedIn: 'root'
})
export class TicketService {

    constructor(private httpClient: HttpClient){}

    processPayment(id: number, date: Date): Observable<Ticket> {
        return this.httpClient.post(BASE_URL + id, date) as Observable<Ticket>;
    }

    getTicketsFromUser(): Observable<Ticket[]> {
        return this.httpClient.get(BASE_URL) as Observable<Ticket[]>
    }
}