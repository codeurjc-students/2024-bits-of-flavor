import { Product } from "./product.model";

export class Offer {
    id: number;
    name: string;
    expDate: Date;
    product: Product;
    discount: number;
    newPrice: number;

    constructor() {
        this.id = 0;
        this.name = "";
        this.expDate = new Date();
        this.product = new Product();
        this.discount = 0;
        this.newPrice = 0;
    }
}