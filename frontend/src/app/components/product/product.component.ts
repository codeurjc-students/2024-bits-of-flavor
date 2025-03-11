import { Component } from '@angular/core';
import { Product } from '../../model/product.model';
import { ProductService } from '../../service/product.service';
import { ActivatedRoute } from '@angular/router';
import { OfferService } from '../../service/offer.service';
import { Offer } from '../../model/offer.model';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
    product: Product = new Product();
    offer: Offer = new Offer();

    constructor(private productService: ProductService, private offerService:OfferService, activatedRoute: ActivatedRoute) {
        let id = activatedRoute.snapshot.params['id'];
        productService.getProduct(id).subscribe(
            (product: Product) => {
                this.product = product
                this.getofferByProduct(product.id)
            }
        );
    }

    public getofferByProduct(productId: number) {
        this.offerService.getOfferByProduct(productId).subscribe(
            (offer: Offer) => this.offer = offer
        );
    }

}