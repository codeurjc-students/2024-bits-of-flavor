import { Product } from "./product.model";

export class Offer {
    id: number;
    name: string;
    expDate: Date;
    discount: number;
    newPrice: number;

    constructor() {
        this.id = 0;
        this.name = "";
        this.expDate = new Date();
        this.discount = 0;
        this.newPrice = 0;
    }
}