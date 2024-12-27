import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { Product } from '../../model/product.model';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit{
  public products: Product[] = [];
  public filtredProducts: Product[] = [];
  public foodTypes: string[] = []
  public selectedCategory: Set<string> = new Set();
  public minPrice: number | null = null;
  public maxPrice: number | null = null;

  constructor(private productService: ProductService, public loginService: LoginService){}

  ngOnInit(){
    this.loadProducts();
  }
  
  public loadProducts() {
    this.productService.getAllProducts().subscribe(
      (products: Product[]) => {
        this.products = products;
        this.foodTypes = [...new Set(this.products.map(p => p.category))];
        this.filtredProducts = products;
      }
    );
  }

  public deleteProduct(id: number){
    this.productService.deleteProduct(id).subscribe(
      () => {
        alert("Producto borrado correctamente");
        this.loadProducts();
      }
    );
  }

  public onCategoryChange(event: Event) {
    const checkbox = event.target as HTMLInputElement;
    if (checkbox.checked) {
      this.selectedCategory.add(checkbox.value);
    } else {
      this.selectedCategory.delete(checkbox.value);
    }
    this.filter();
  }

  public filter() {
    this.filtredProducts = this.products.filter(product => {
      const isCategory =
        this.selectedCategory.size === 0 ||
        this.selectedCategory.has(product.category);
      const isMinPrice = this.minPrice === null || product.price >= this.minPrice;
      const isMaxPrice = this.maxPrice === null || product.price <= this.maxPrice;
      return isCategory && isMinPrice && isMaxPrice;
    });
  }

}
