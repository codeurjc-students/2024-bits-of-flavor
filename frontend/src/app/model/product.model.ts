export class Product {
    id: number;
    name: string;
    info: string;
    weight: number;
    price: number;
    cal: number;
    protein: number;
    fat: number;
    carbo: number;
    category: string;

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
    }
}