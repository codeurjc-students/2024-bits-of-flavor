import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Product } from "../model/product.model";
import { Injectable } from "@angular/core";

const BASE_URL = "/api/products/";

@Injectable({
    providedIn: 'root'
})

export class ProductService {
    constructor(private httpClient: HttpClient){}

    getAllProducts(): Observable<Product[]> {
        return this.httpClient.get(BASE_URL) as Observable<Product[]>;
    }
}