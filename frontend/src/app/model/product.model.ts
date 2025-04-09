import { Offer } from "./offer.model";

export class Product {
    id: number;
    name: string;
    info: string;
    weight: number | null;
    price: number | null;
    cal: number | null;
    protein: number | null;
    fat: number | null;
    carbo: number | null;
    category: string;
    active: boolean;
    ticketSize: number;

    constructor() {
        this.id = 0;
        this.name = "";
        this.info = "";
        this.weight = 0;
        this.price = 0;
        this.cal = 0;
        this.protein = 0;
        this.fat = 0;
        this.carbo = 0;
        this.category = "";
        this.active = false;
        this.ticketSize = 0;
    }
}