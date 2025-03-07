import { Injectable } from "@angular/core";
import { Offer } from "../model/offer.model";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

const BASE_URL = '/api/offers/';

@Injectable({ providedIn: 'root' })

export class OfferService{
    
    constructor(private httpClient:HttpClient){}

    public addOffer (offer: Offer, productId: number):Observable<Offer> {
        return this.httpClient.post(BASE_URL + productId, offer) as Observable<Offer>;
    }

    public getAllOffers(): Observable<Offer[]>{
        return this.httpClient.get(BASE_URL) as Observable<Offer[]>;
    }

    public deleteOffer(id: number): Observable<Offer>{
        return this.httpClient.delete(BASE_URL + id) as Observable<Offer>;
    }
}