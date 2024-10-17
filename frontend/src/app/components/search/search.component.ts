import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { Product } from '../../model/product.model';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit{
  public products: Product[] = []

  constructor(private productService: ProductService){}

  ngOnInit(){
    this.loadProducts();
  }
  
  public loadProducts() {
    this.productService.getAllProducts().subscribe(
      (products: Product[]) => this.products = products
    );
  }

}
