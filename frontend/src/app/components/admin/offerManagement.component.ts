import { Component, OnInit } from "@angular/core";
import { Offer } from "../../model/offer.model";
import { Product } from "../../model/product.model";
import { ProductService } from "../../service/product.service";
import { OfferService } from "../../service/offer.service";
import { HttpErrorResponse } from "@angular/common/http";

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

    constructor(private offerService: OfferService, private productService: ProductService){}

    ngOnInit(){
        this.loadProducts();
      }
      
      public loadProducts() {
        this.productService.getAllProducts().subscribe(
          (products: Product[]) => {
            this.products = products;
          }
        );
      }

    public addOffer(){
        if(this.checkFields()){
            this.offerService.addOffer(this.offer, this.productId).subscribe({
                next: (offer: Offer) => {
                    console.log(offer);
                    this.offer = new Offer();
                    this.productId = 0;
                },
                error: (e: HttpErrorResponse) => console.log(e)
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
        const tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        tomorrow.setHours(0, 0, 0, 0);
        if(new Date(this.offer.expDate) < tomorrow){
            alert("ERROR: Seleccione fecha futura");
            return false
        }
        if(this.productId == 0){
            alert("ERROR: Seleccione producto");
            return false
        }
        return true;
    }
}