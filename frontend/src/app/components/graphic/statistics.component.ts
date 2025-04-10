import { Component, OnInit } from "@angular/core";
import { Chart } from "chart.js/auto";
import { ProductService } from "../../service/product.service";
import { Product } from "../../model/product.model";
import { Router } from "@angular/router";
import { HttpErrorResponse } from "@angular/common/http";
import { LoginService } from "../../service/login.service";
import { Ticket } from "../../model/ticket.model";
import { TicketService } from "../../service/ticket.service";


@Component({
    selector: 'app-statistics',
    templateUrl: './statistics.component.html',
    styleUrl: './statistics.component.css'
})

export class StatisticsComponent implements OnInit {

    public tickets: Ticket[] = [];
    public hasMore: boolean = true;

    constructor(private productService: ProductService, private router: Router, public loginService: LoginService, private ticketService: TicketService) {
    }

    ngOnInit() {
        this.loadData();
        this.ticketService.resetPage();
        this.loadMore();
    }

    public loadData() {
        this.productService.getAllProducts().subscribe({
            next: (products: Product[]) => {
                const data: { name: string; price: number }[] = [];

                products.forEach(product => {
                    data.push({ name: product.name, price: product.ticketSize });
                })
                new Chart(
                    "acquisitions",
                    {
                        type: 'bar',
                        data: {
                            labels: data.map(row => row.name),
                            datasets: [
                                {
                                    label: 'Tickets en 2 semanas:',
                                    data: data.map(row => row.price),
                                    borderColor: 'black',
                                    backgroundColor: 'red',
                                }
                            ]
                        },
                        options: {
                            scales: {
                                x:{
                                    ticks: {
                                        display: false
                                    }
                                },
                                y:{
                                    ticks: { precision: 0 },
                                }
                            }
                        }
                    }
                );
            },
            error: (e: HttpErrorResponse) => {
                console.log(e);
                this.router.navigate(["/error"]);
            }
    });
    }

    public loadMore() {
        this.ticketService.getPaginatedTickets().subscribe({
            next: (tickets) => {
                if (tickets.last) {
                    this.hasMore = false; // Si trae menos de 10, no hay más datos
                }
                this.tickets = [...this.tickets, ...tickets.content];
                this.ticketService.nextPage();
            },
            error: (e: HttpErrorResponse) => {
                console.log(e);
                this.router.navigate(["/error"]);
            }
        });
    }

    public claimTicket(ticket: Ticket) {
        ticket.claimed = !ticket.claimed;
        this.ticketService.updateTicket(ticket).subscribe({
            next: (updated: Ticket)=>{
                ticket = updated;
            },
            error: (e: HttpErrorResponse)=>{
                ticket.claimed = !ticket.claimed;
                console.log(e);
                alert("ERROR: NO se ha podido reclamar el ticket");
            }
    });
    }

}