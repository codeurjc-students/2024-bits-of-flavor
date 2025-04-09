import { Offer } from "./offer.model";
import { Product } from "./product.model";
import { User } from "./user.model";

export class Ticket {
    id: number;
    date: Date;
    user: User;
    product: Product;
    offer: Offer;
    active: boolean;
    claimed: boolean;

    constructor(){
        this.id = 0;
        this.date = new Date();
        this.user = new User();
        this.product = new Product();
        this.offer = new Offer();
        this.active = false;
        this.claimed = false;
    }

    isWithinNextTwoWeeks(date: Date): boolean {
        const today = new Date();
        const twoWeeksLater = new Date(today);
        twoWeeksLater.setDate(today.getDate() + 14);
        return date >= today && date <= twoWeeksLater;
    }
}