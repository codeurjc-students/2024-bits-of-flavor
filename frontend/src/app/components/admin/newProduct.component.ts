import { Component, ViewChild } from "@angular/core";
import { Product } from "../../model/product.model";
import { ProductService } from "../../service/product.service";
import { Router } from "@angular/router";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
    selector: 'app-new-product',
    templateUrl: './newProduct.component.html',
    styleUrl: './newProduct.component.css'
})

export class NewProduct {

    product: Product = new Product();
    imageSrc: String = "assets/images/default-profile-picture.jpg";

    @ViewChild("file")
    file: any;

    constructor(private productService: ProductService, private router: Router){
        this.product.weight = null;
        this.product.price = null;
        this.product.cal = null;
        this.product.protein = null;
        this.product.carbo = null;
        this.product.fat = null;
    }

    submitNewProductForm(){
        if (this.product.name != "" && this.product.weight != 0 && this.product.price != 0){
            this.productService.newProduct(this.product).subscribe({
                next: (product: Product) => {
                    this.updateImage(product);
                    this.router.navigate(["/"]);
                },
                error: (e: HttpErrorResponse) => {
                    console.log(e);
                    alert("ERROR: NO se ha podido añadir el producto");
                }
        });
        }
    }

    public updateImage(product: Product){
        const image = this.file.nativeElement.files[0];
        if (image) {
          const data = new FormData();
          data.append('imageFile', image);
          this.productService.setProductImage(product, data).subscribe();
        }
    }

    public modifyImageFile(event: any){
        const reader = new FileReader();
        reader.onload = (event: any) => {
          this.imageSrc = event.target.result;
        };
        reader.readAsDataURL(event.target.files[0]);
    }
}