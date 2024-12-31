import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Product } from "../model/product.model";
import { Injectable } from "@angular/core";

const BASE_URL = "/api/product/";

@Injectable({
    providedIn: 'root'
})

export class ProductService {
    constructor(private httpClient: HttpClient){}

    getAllProducts(): Observable<Product[]> {
        return this.httpClient.get(BASE_URL) as Observable<Product[]>;
    }

    getProduct(id: number): Observable<Product> {
        return this.httpClient.get(BASE_URL + id) as Observable<Product>;
    }

    newProduct(product: Product): Observable<Product> {
        return this.httpClient.post(BASE_URL, product) as Observable<Product>;
    }

    setProductImage(product: Product, data: FormData): Observable<Product> {
        return this.httpClient.put(BASE_URL + product.id + "/image", data) as Observable<Product>
    }

    deleteProduct(id: number): Observable<Product> {
        return this.httpClient.delete(BASE_URL + id) as Observable<Product>;
    }
}