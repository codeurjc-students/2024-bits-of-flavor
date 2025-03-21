import { Component, OnInit } from "@angular/core";
import { Chart } from "chart.js/auto";
import { ProductService } from "../../service/product.service";
import { Product } from "../../model/product.model";


@Component({
    selector: 'app-statistics',
    templateUrl: './statistics.component.html',
    styleUrl: './statistics.component.css'
})

export class StatisticsComponent implements OnInit {

    constructor(private productService:ProductService) {
    }

    ngOnInit() {
        this.loadData();
    }

    public loadData() {
        this.productService.getAllProducts().subscribe(
            (products: Product[]) => {
                const data: { name: string; price: number }[] = [];
        
                products.forEach(product =>{
                    data.push({name: product.name, price: product.ticketSize });
                })
                new Chart(
                    "acquisitions",
                    {
                        type: 'bar',
                        data: {
                            labels: data.map(row => row.name),
                            datasets: [
                                {
                                    label: 'Tickets in next 2 weeks:',
                                    data: data.map(row => row.price)
                                }
                            ]
                        }
                    }
                );
            }
        );
    }

}