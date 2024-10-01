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

@NgModule({
 declarations: [AppComponent,
    HeaderComponent,
    SearchComponent,
    HomeComponent,
    ProductComponent,
    LoginComponent],
 imports: [BrowserModule, FormsModule, routing, HttpClientModule],
 bootstrap: [AppComponent]
})

export class AppModule { }
