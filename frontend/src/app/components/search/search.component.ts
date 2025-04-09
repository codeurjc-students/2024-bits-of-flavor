import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { Product } from '../../model/product.model';
import { LoginService } from '../../service/login.service';
import { OfferService } from '../../service/offer.service';
import { Offer } from '../../model/offer.model';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {
  public products: Product[] = [];
  public offers: Offer[] = [];
  public filtredProducts: Product[] = [];
  public foodTypes: string[] = []
  public selectedCategory: Set<string> = new Set();
  public minPrice: number | null = null;
  public maxPrice: number | null = null;
  public activeOffer: boolean | null = null;

  constructor(private productService: ProductService, private offerService: OfferService, public loginService: LoginService, private router: Router) { }

  ngOnInit() {
    this.loadProducts();
    this.loadActiveOffers();
  }

  public loadProducts() {
    this.productService.getAllProducts().subscribe({
      next: (products: Product[]) => {
        this.products = products;
        this.foodTypes = [...new Set(this.products.map(p => p.category))];
        this.filtredProducts = products;
      },
      error: (e: HttpErrorResponse) => {
        console.log(e);
        this.router.navigate(["/error"]);
      }
    });
  }

  public loadActiveOffers() {
    this.offerService.getAllOffers().subscribe({
      next: (offers: Offer[]) => {
        this.offers = offers.filter(offer => offer.active);
      },
      error: (e: HttpErrorResponse) => {
        console.log(e);
        this.router.navigate(["/error"]);
      }
    });
  }

  public deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe({
      next: () => {
        alert("Producto borrado correctamente");
        this.loadProducts();
      },
      error: (e: HttpErrorResponse) => {
        console.log(e);
        alert("ERROR: NO se ha podido borrar el producto");
    }
  });
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

  public onActiveChange(isActiveOffer: boolean | null) {
    if (isActiveOffer) {
      this.activeOffer = true;
    } else if (isActiveOffer === null) {
      this.activeOffer = null;
    } else {
      this.activeOffer = false;
    }
    this.filter();
  }

  public filter() {
    this.filtredProducts = this.products.filter(product => {
      const isCategory =
        this.selectedCategory.size === 0 ||
        this.selectedCategory.has(product.category);
      const isMinPrice = this.minPrice === null || product.price != null && product?.price >= this.minPrice;
      const isMaxPrice = this.maxPrice === null || product.price != null && product?.price <= this.maxPrice;
      const isActiveOffer = this.activeOffer === null || product.active === this.activeOffer;
      return isCategory && isMinPrice && isMaxPrice && isActiveOffer;
    });
  }

  public getOffer(productId: number) {
    return this.offers.find(offer => offer.product.id === productId) || null;
  }

}
