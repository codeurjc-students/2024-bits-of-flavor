import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { SearchComponent } from './components/search/search.component';
import { HomeComponent } from './components/home/home.component';
import { routing } from './app.routing';
import { HttpClientModule } from '@angular/common/http';
import { ProductComponent } from './components/product/product.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { PaymentComponent } from './components/payment/payment.component';
import { SignupComponent } from './components/signup/signup.component';
import { NewProduct } from './components/admin/newProduct.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
 declarations: [AppComponent,
   HeaderComponent,
   SearchComponent,
   HomeComponent,
   ProductComponent,
   LoginComponent,
   ProfileComponent,
   PaymentComponent,
   SignupComponent,
   NewProduct,
   FooterComponent],
 imports: [BrowserModule, FormsModule, routing, HttpClientModule],
 bootstrap: [AppComponent]
})

export class AppModule { }
