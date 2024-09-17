import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { SearchComponent } from './components/search/search.component';
import { HomeComponent } from './components/home/home.component';
import { routing } from './app.routing';

@NgModule({
 declarations: [AppComponent, HeaderComponent, SearchComponent, HomeComponent],
 imports: [BrowserModule, FormsModule, routing],
 bootstrap: [AppComponent]
})

export class AppModule { }
