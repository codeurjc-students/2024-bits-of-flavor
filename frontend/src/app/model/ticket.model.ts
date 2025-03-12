import { Offer } from "./offer.model";
import { Product } from "./product.model";
import { User } from "./user.model";

export class Ticket {
    id: number;
    date: Date;
    user: User;
    product: Product;
    offer: Offer;

    constructor(){
        this.id = 0;
        this.date = new Date();
        this.user = new User();
        this.product = new Product();
        this.offer = new Offer();
    }
}