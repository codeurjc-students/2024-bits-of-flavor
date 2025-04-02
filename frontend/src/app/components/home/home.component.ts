import { Component, OnInit } from '@angular/core';
import { Product } from '../../model/product.model';
import { ProductService } from '../../service/product.service';
import { OfferService } from '../../service/offer.service';
import { Offer } from '../../model/offer.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  public products: Product[] = [];
  public offers: Offer[] = [];

  constructor(private productService: ProductService, private offerService: OfferService){}

  ngOnInit() {
    this.loadProducts();
    this.loadActiveOffers();
  }

  public loadProducts() {
    this.productService.getRecommendedProducts().subscribe(
      (products: Product[]) => {
        this.products = products;
      }
    );
  }

  public loadActiveOffers(){
    this.offerService.getAllOffers().subscribe(
      (offers: Offer[]) => {
        this.offers = offers.filter(offer => offer.active);
      }
    )
  }

  public getOffer(productId: number){
    return this.offers.find(offer => offer.product.id === productId) || null;
  }

}
