import { Component } from '@angular/core';
import { Product } from '../../model/product.model';
import { ProductService } from '../../service/product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
    product: Product = new Product();

    constructor(private productService: ProductService, activatedRoute: ActivatedRoute) {
        let id = activatedRoute.snapshot.params['id'];
        productService.getProduct(id).subscribe(
            (product: Product) => this.product = product
        );
    }

}