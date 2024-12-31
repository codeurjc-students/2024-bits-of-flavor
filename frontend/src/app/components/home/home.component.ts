import { Component, OnInit } from '@angular/core';
import { Product } from '../../model/product.model';
import { ProductService } from '../../service/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  public products: Product[] = [];

  constructor(private productService: ProductService){}

  ngOnInit() {
    this.loadProducts();
  }

  public loadProducts() {
    this.productService.getAllProducts().subscribe(
      (products: Product[]) => {
        this.products = products;
      }
    );
  }

}
