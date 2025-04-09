import { Component, OnInit } from "@angular/core";
import { Offer } from "../../model/offer.model";
import { Product } from "../../model/product.model";
import { ProductService } from "../../service/product.service";
import { OfferService } from "../../service/offer.service";
import { HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";

@Component({
    selector: 'app-offer-management',
    templateUrl: './offerManagement.component.html',
    styleUrl: './offerManagement.component.css'
})

export class OfferManagement implements OnInit{

    public offer: Offer = new Offer();
    public offers: Offer[] = [];
    public products: Product[] = [];
    public productId: number = 0;

    tomorrow = new Date();
    public hasMore: boolean = true;

    constructor(private offerService: OfferService, private productService: ProductService, private router: Router){}

    ngOnInit(){
        this.tomorrow.setDate(this.tomorrow.getDate() + 1);
        this.tomorrow.setHours(0, 0, 0, 0);
        this.offerService.resetPage();
        this.offerService.setLimit(10);
        this.loadMore();
        this.loadProducts();  
    }
      
    public loadProducts() {
        const today = new Date();
        this.productService.getAllProducts().subscribe({
          next: (products: Product[]) => {
            this.products = products.filter(product => !product.active);
          },
          error: (e: HttpErrorResponse) => {
            console.log(e);
            this.router.navigate(["/error"]);
        }
    });
    }

    public loadMore() {
        this.offerService.getPaginatedOffers(false).subscribe({
          next: (offers) => {
            if (offers.last) {
              this.hasMore = false;
            }
            this.offerService.nextPage();
            this.offers = [...this.offers, ...offers.content];
          },
          error: (e: HttpErrorResponse) => {
            console.log(e);
            this.router.navigate(["/error"]);
          }
        });
      }

    public addOffer(){
        if(this.checkFields()){
            this.offerService.addOffer(this.offer, this.productId).subscribe({
                next: (offer: Offer) => {
                    console.log(offer);
                    this.productId = 0;
                    this.offerService.resetPage();
                    this.hasMore = true;
                    this.offers = [];
                    this.loadMore();
                    this.loadProducts();
                },
                error: (e: HttpErrorResponse) => {
                    console.log(e);
                    alert("ERROR: NO se ha podido añadir la oferta")
                }
        });
        }
    }

    public checkFields(): boolean{
        if(this.offer.name == ""){
            alert("ERROR: Seleccione nombre");
            return false
        }
        if(this.offer.discount <= 0 || this.offer.discount >90){
            alert("ERROR: Seleccione descuento entre 1 y 90");
            return false
        }
        
        if(new Date(this.offer.expDate) < this.tomorrow){
            alert("ERROR: Seleccione fecha futura");
            return false
        }
        if(this.productId == 0){
            alert("ERROR: Seleccione producto");
            return false
        }
        return true;
    }

    public deleteOffer(id: number) {
        if(confirm('¿Borrar oferta?')){
            this.offerService.deleteOffer(id).subscribe({
                next: () => {
                    alert('Oferta borrada con exito');
                    this.offers = this.offers.filter(offer => offer.id !== id);
                    this.loadProducts();
                },
                error: (e: HttpErrorResponse) => {
                    console.log(e);
                    alert('ERROR: NO se ha podido borrar la oferta');
                }
            })
        }
    }
}