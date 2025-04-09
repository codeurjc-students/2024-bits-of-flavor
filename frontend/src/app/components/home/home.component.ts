import { Component, OnInit } from '@angular/core';
import { Product } from '../../model/product.model';
import { ProductService } from '../../service/product.service';
import { OfferService } from '../../service/offer.service';
import { Offer } from '../../model/offer.model';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  public products: Product[] = [];
  public offers: Offer[] = [];
  public subListOffers: Offer[] = [];
  public hasNext: boolean = true;
  public hasPrev: boolean = true;

  constructor(private productService: ProductService, private offerService: OfferService, private router: Router) { }

  ngOnInit() {
    this.offerService.resetPage();
    this.offerService.setLimit(3);
    this.loadProducts();
    this.loadActiveOffers();
    this.loadNext();
  }

  public loadProducts() {
    this.productService.getRecommendedProducts().subscribe({
      next: (products: Product[]) => {
        this.products = products;
      },
      error: (e: HttpErrorResponse) => {
        console.log(e);
        this.router.navigate(["/error"]);
      }
    });
  }

  public loadActiveOffers() {
    this.offerService.getAllOffers().subscribe(
      (offers: Offer[]) => {
        this.offers = offers.filter(offer => offer.active);
      }
    )
  }

  public loadNext() {
    this.offerService.getPaginatedOffers(true).subscribe({
      next: (offers) => {
        this.hasPrev = true;
        if (offers.first) {
          this.hasPrev = false;
        }
        if (offers.last) {
          this.hasNext = false;
          this.offerService.prevPage();
        }
        this.offerService.nextPage();
        this.subListOffers = offers.content;
      },
      error: (e: HttpErrorResponse) => {
        console.log(e);
        this.router.navigate(["/error"]);
      }
    });
  }

  public loadPrev() {
    this.offerService.prevPage();
    this.offerService.getPaginatedOffers(true).subscribe({
      next: (offers) => {
        this.hasNext = true;
        if (offers.first) {
          this.hasPrev = false;
          this.offerService.nextPage();
        }
        if (offers.last) {
          this.hasNext = false;
        }
        this.subListOffers = offers.content;
      },
      error: (e: HttpErrorResponse) => {
        console.log(e);
        this.router.navigate(["/error"]);
      }
    });
  }

  public getOffer(productId: number) {
    return this.offers.find(offer => offer.product.id === productId) || null;
  }

}
