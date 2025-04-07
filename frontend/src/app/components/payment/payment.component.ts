import { Component, OnInit } from "@angular/core";
import { TicketService } from "../../service/ticket.service";
import { Product } from "../../model/product.model";
import { ActivatedRoute, Router } from "@angular/router";
import { ProductService } from "../../service/product.service";
import { Ticket } from "../../model/ticket.model";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})

export class PaymentComponent implements OnInit {
  public product: Product = new Product();

  public date: Date = new Date();

  constructor(private ticketService: TicketService, private productService: ProductService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    let id = this.activatedRoute.snapshot.params['id'];
    this.productService.getProduct(id).subscribe(
      (product: Product) => this.product = product
    );
  }

  public processPayment() {
    this.ticketService.processPayment(this.product.id, new Date(this.date)).subscribe({
      next: (ticket: Ticket) => {
        console.log(ticket);
        this.router.navigate(['/']);
      },
      error: (e: HttpErrorResponse) => {
        console.log(e);
        this.router.navigate(["/error"]);
      }
    });
  }
}