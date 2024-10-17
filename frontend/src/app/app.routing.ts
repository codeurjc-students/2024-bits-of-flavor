import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';
import { ProductComponent } from './components/product/product.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';

const appRoutes: Routes = [
 { path: 'home', component: HomeComponent},
 { path: 'search', component: SearchComponent},
 { path: '', redirectTo: 'home', pathMatch: 'full' },
 { path: 'product/:id', component: ProductComponent},
 { path: 'login', component: LoginComponent},
 { path: 'profile', component: ProfileComponent}
]

export const routing = RouterModule.forRoot(appRoutes)
