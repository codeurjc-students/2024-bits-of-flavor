import { Component, OnInit } from "@angular/core";
import { TicketService } from "../../service/ticket.service";
import { Product } from "../../model/product.model";
import { ActivatedRoute, Router } from "@angular/router";
import { ProductService } from "../../service/product.service";
import { Ticket } from "../../model/ticket.model";
import { HttpErrorResponse } from "@angular/common/http";
import { OfferService } from "../../service/offer.service";
import { Offer } from "../../model/offer.model";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})

export class PaymentComponent implements OnInit {
  public product: Product = new Product();
  public offer: Offer = new Offer();
  public date: Date = new Date();
  tomorrow = new Date();

  constructor(private ticketService: TicketService, private productService: ProductService,
    private activatedRoute: ActivatedRoute, private router: Router,
    private offerService: OfferService
  ) { }

  ngOnInit(): void {
    let id = this.activatedRoute.snapshot.params['id'];
    this.productService.getProduct(id).subscribe({
      next: (product: Product) => {
        this.product = product;
        if (product.active) {
          this.offerService.getOfferByProduct(product.id).subscribe(
            (offer: Offer) => this.offer = offer
          );
        }
      },
      error: (e: HttpErrorResponse) => {
        console.log(e);
        this.router.navigate(["/error"]);
      }
    });
    this.tomorrow.setDate(this.tomorrow.getDate() + 1);
    this.tomorrow.setHours(0, 0, 0, 0);
  }

  public processPayment() {
    if (new Date(this.date) < this.tomorrow) {
      alert("ERROR: Seleccione fecha futura");
      return;
    }

    this.ticketService.processPayment(this.product.id, new Date(this.date)).subscribe({
      next: (ticket: Ticket) => {
        this.router.navigate(['/']);
      },
      error: (e: HttpErrorResponse) => {
        console.log(e);
        this.router.navigate(["/error"]);
      }
    });
  }
}