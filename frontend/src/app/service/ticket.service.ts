import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Ticket } from "../model/ticket.model";

const BASE_URL = "/api/ticket/"

@Injectable({
  providedIn: 'root'
})

export class TicketService {

  private page = 1;
  private limit = 10;

  constructor(private httpClient: HttpClient) { }

  processPayment(id: number, date: Date): Observable<Ticket> {
    return this.httpClient.post(BASE_URL + id, date) as Observable<Ticket>;
  }

  getTicketsFromUser(): Observable<Ticket[]> {
    return this.httpClient.get(BASE_URL) as Observable<Ticket[]>;
  }

  getPaginatedTicketsFromUser(): Observable<any> {
    const params = {
      page: this.page.toString(),
      size: this.limit.toString(),
    };
    return this.httpClient.get(BASE_URL, { params }) as Observable<any>;
  }

  getPaginatedTickets(): Observable<any> {
    const params = {
      page: this.page.toString(),
      size: this.limit.toString(),
    };
    return this.httpClient.get(BASE_URL + "all", { params }) as Observable<any>;
  }

  updateTicket(ticket: Ticket): Observable<Ticket>{
    return this.httpClient.put(BASE_URL + ticket.id, ticket) as Observable<Ticket>;
  }

  nextPage() {
    this.page++;
  }

  resetPage() {
    this.page = 0;
  }
}