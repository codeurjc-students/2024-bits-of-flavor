import { Injectable } from "@angular/core";
import { Offer } from "../model/offer.model";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

const BASE_URL = '/api/offers/';

@Injectable({ providedIn: 'root' })

export class OfferService {

    private page = 0;
    private limit = 10;

    public setLimit(value:number) {
        this.limit = value;
    }

    constructor(private httpClient: HttpClient) { }

    public addOffer(offer: Offer, productId: number): Observable<Offer> {
        return this.httpClient.post(BASE_URL + productId, offer) as Observable<Offer>;
    }

    public getAllOffers(): Observable<Offer[]> {
        return this.httpClient.get(BASE_URL) as Observable<Offer[]>;
    }

    public getPaginatedOffers(active: boolean = false): Observable<any> {
        const params = {
            page: this.page.toString(),
            size: this.limit.toString(),
            active: active.toString(),
        };
        return this.httpClient.get(BASE_URL + "page", { params }) as Observable<any>;
    }

    public getOfferByProduct(productId: number): Observable<Offer> {
        return this.httpClient.get(BASE_URL + productId) as Observable<Offer>;
    }

    public deleteOffer(id: number): Observable<Offer> {
        return this.httpClient.delete(BASE_URL + id) as Observable<Offer>;
    }

    nextPage() {
        this.page++;
    }

    prevPage() {
        this.page--;
    }

    resetPage() {
        this.page = 0;
    }


}