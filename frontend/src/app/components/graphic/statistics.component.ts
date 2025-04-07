import { Component, OnInit } from "@angular/core";
import { Chart } from "chart.js/auto";
import { ProductService } from "../../service/product.service";
import { Product } from "../../model/product.model";
import { Router } from "@angular/router";
import { HttpErrorResponse } from "@angular/common/http";


@Component({
    selector: 'app-statistics',
    templateUrl: './statistics.component.html',
    styleUrl: './statistics.component.css'
})

export class StatisticsComponent implements OnInit {

    constructor(private productService: ProductService, private router: Router) {
    }

    ngOnInit() {
        this.loadData();
    }

    public loadData() {
        this.productService.getAllProducts().subscribe({
            next: (products: Product[]) => {
                const data: { name: string; price: number }[] = [];

                products.forEach(product => {
                    data.push({ name: product.name, price: product.ticketSize });
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
                                    data: data.map(row => row.price),
                                    borderColor: 'black',
                                    backgroundColor: 'red',
                                }
                            ]
                        }
                    }
                );
            },
            error: (e: HttpErrorResponse) => {
                console.log(e);
                this.router.navigate(["/error"]);
            }
    });
    }

}