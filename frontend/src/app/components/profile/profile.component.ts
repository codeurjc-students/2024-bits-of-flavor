import { Component, OnInit, ViewChild } from "@angular/core";
import { User } from "../../model/user.model";
import { UserService } from "../../service/user.service";
import { LoginService } from "../../service/login.service";
import { Ticket } from "../../model/ticket.model";
import { TicketService } from "../../service/ticket.service";
import { Product } from "../../model/product.model";
import jsPDF from "jspdf";

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrl: './profile.component.css'
  })

  export class ProfileComponent implements OnInit{

    user: User = new User();
    product: Product = new Product();
    imageSrc: String = "assets/images/default-profile-picture.jpg"
    editAttrUsername: boolean = false;
    editAttrEmail: boolean = false;
    editAttrPhoneNumber: boolean = false;

    tickets: Ticket[] = [];

    @ViewChild("file")
    file: any;

    constructor(private loginService: LoginService, private userService: UserService, private ticketService: TicketService){}

    ngOnInit() {
        this.loadCurrentUser();
    }

    public loadCurrentUser(){
        this.user = this.loginService.getUser();
        this.imageSrc = "/api/user/" + this.user.id + "/image";
        this.ticketService.getTicketsFromUser().subscribe(
            (tickets: Ticket[]) => this.tickets = tickets
        );
    }

    public editUsername(){
        this.editAttrUsername = true;
    }

    public editEmail(){
        this.editAttrEmail = true;
    }

    public editPhoneNumber(){
        this.editAttrPhoneNumber = true;
    }

    public confirmUsername(){
        this.updateUser();
        this.editAttrUsername = false;
    }

    public confirmEmail(){
        this.updateUser();
        this.editAttrEmail = false;
    }

    public confirmPhoneNumber(){
        this.updateUser();
        this.editAttrPhoneNumber = false;
    }

    public updateUser(){
        this.userService.updateUser(this.user).subscribe(
            () => this.loadCurrentUser()
        );
    }

    public updateImage(){
        const image = this.file.nativeElement.files[0];
        if (image) {
          const data = new FormData();
          data.append('imageFile', image);
          this.userService.setUserImage(this.user, data).subscribe(
            () => this.loadCurrentUser()
          );
        }
    }

    public modifyImageFile(event: any){
        const reader = new FileReader();
        reader.onload = (event: any) => {
          this.imageSrc = event.target.result;
        };
        reader.readAsDataURL(event.target.files[0]);
        this.updateImage();
    }

    public downloadTicket(ticket: Ticket){
        const doc = new jsPDF();
        const pageWidth = doc.internal.pageSize.getWidth(); // Obtiene el ancho de la página
        const marginLeft = 10;
        const marginRight = 10;

        doc.setFont('helvetica', 'bold');
        doc.setFontSize(18);
        doc.text('Bits of Flavor', 20, 20);
      
        doc.setFontSize(12);
        doc.setFont('helvetica', 'normal');
        doc.text(`Cliente: ${ticket.user.username}`, 20, 40);

        doc.setFont('helvetica', 'bold');
        doc.text('Información de Recogida', 20, 60);
        doc.setFont('helvetica', 'normal');
        doc.text(`Fecha: ${ticket.date.toString()}`, 20, 70);
        doc.text('Lugar: Universidad Rey Juan Carlos (Móstoles)', 20, 80);
        doc.text('Modo de recogida: Presentar este PDF en Cafetería', 20, 90);
        
        doc.setFont('helvetica', 'bold');
        doc.text('Detalles del Producto', 20, 110);
        doc.setFont('helvetica', 'normal');
        doc.text(`Producto: ${ticket.product.name}`, 20, 120);
        doc.text(`Precio: ${ticket.product.price.toFixed(2)} €`, 20, 130);
        if (ticket.offer != null){
            doc.setFont('helvetica', 'bold');
            doc.text('OFERTA', 20, 150);
            doc.setFont('helvetica', 'normal');
            doc.text(`Nombre de oferta: ${ticket.offer.name}`, 20, 160);
            doc.text(`Descuento: ${ticket.offer.discount} %`, 20, 170);
            doc.text(`Precio: ${ticket.offer.newPrice.toFixed(2)} €`, 20, 180);
        }
      
        const qrImg = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABIMAAASDAgMAAACXZRdkAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJUExURf///z8/PwAAAABE24UAAAfdSURBVHja7d2xceMwEAVQjgMFKoVVXBNKVMJVwSacK3AgscqL7ASzxAIkJfr0fioI2H1Id8BhkMV8ICBEiBAhQoQIERJChAgRIkSIECEhRIgQIUKECBESQoQIESJEiBAhIUSIECFChAgREkKECBEiRIgQISFEiBAhQoQIERJChAgRIkSIECEhRIgQoeMLnecVuffsMn3/adtdujIRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFC/4PQ7Xvt2LHLPZ48fJ7Q45LO9ZBCn/kG+oS+8gOjp0MK/ck38JcQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKE+oTqM22nROHxQT3tl4hTKJQ4iBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIEfpFQmVV8UFlVfVRx3IaciBEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoTeRGho6C1eMjcgEiJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQr9RqJ6MUENvLc4lYiGUCCFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAi1CT0u6VwPKfSZb2DuEurJoYR68jyh+FO9T1tCiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIHU5om8Qf/B3qQ4r3ntua9mmEECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgR2lAoMRiY+Jpv4inBVQOTLQeVV/HT/rpJT0KECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRGh7oXi/YdsJxK5atjmIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgReopQQ/v3hl2GNaOOC0sSQ4rFL7ewJkKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRGhXoaKqOJnn+4qq4m8CtyAeYdKTECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRWhbqSjwY2NJ+AjFekkhXLYQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiNCeQg37/WSvdwLjJXFu9Qud180xEiJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQo2vDcaFJ4YU411ariLxlGBcy0L7hAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSI0GuEVs0xfvRUlbiKoS7UNaQY70KIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAjtINT1CGBiArGlqnr75W2N4cDkuecqCBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQoR2EtknXO4ENhQ89VxE/SEiIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAg9RSg+ciE944VxuoTmhvbjcgkRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIEKE9hYaeqoreWjJ1tL/uKggRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIEKEXC4319qf6kWMDVaKWbUOIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAi9SGhVWt4sHMPCz/WrSBx0bjCbCBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQoR2FWvab670lZgd73gmcG2YqE48jxh8fJkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChI4rVA4G1qtKCK2bYyxqmeNyF/Z7XNK5vqfQV77uEyFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAgRIkSIECFChAi9qVB9dvAUDym29JYYA0y03zPqGIcQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBE6nFBijnHeSWjVkokQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBH6PUJFVQuvDTY4lxn3OYgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBHaVaiehdcGx7k966YhY6EWREKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBEiRIgQIUKECBFaEnpc0rm+p9C8pjdC7b0Va291xHgCca4LDfFtESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQoQIESJEiBAhQq8REkKECBEiRIgQISFEiBAhQoQIERJChAgRIkSIECEhRIgQIUKECBESQoQIESJEiBAhIUSIECFChAgREkKECBEiRIgQIUIICBEiRIgQIUKEhBAhQoSOLfQPo9dNYRDWvZ8AAAAASUVORK5CYII='; // Your base64-encoded image string here
        doc.addImage(qrImg, 'PNG', 20, 200, 50, 50);

        // Trigger PDF download
        doc.save(`ticket-${ticket.id}.pdf`);

    }
  }