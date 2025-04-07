import { Component } from '@angular/core';
import { Product } from '../../model/product.model';
import { ProductService } from '../../service/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { OfferService } from '../../service/offer.service';
import { Offer } from '../../model/offer.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html',
    styleUrl: './product.component.css'
})
export class ProductComponent {
    product: Product = new Product();
    offer: Offer = new Offer();

    constructor(private productService: ProductService, private offerService: OfferService, activatedRoute: ActivatedRoute, private router: Router) {
        let id = activatedRoute.snapshot.params['id'];
        productService.getProduct(id).subscribe({
            next: (product: Product) => {
                this.product = product
                this.getofferByProduct(product.id)
            },
            error: (e: HttpErrorResponse) => {
                console.log(e);
                this.router.navigate(["/error"]);
            }
    });
    }

    public getofferByProduct(productId: number) {
        this.offerService.getOfferByProduct(productId).subscribe({
            next: (offer: Offer) => {this.offer = offer},
            error: (e: HttpErrorResponse) => {
                console.log(e);
                this.router.navigate(["/error"]);
            }
        });
    }

}