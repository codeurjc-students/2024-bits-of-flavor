import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';
import { ProductComponent } from './components/product/product.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { PaymentComponent } from './components/payment/payment.component';
import { SignupComponent } from './components/signup/signup.component';

const appRoutes: Routes = [
 { path: 'home', component: HomeComponent},
 { path: 'search', component: SearchComponent},
 { path: '', redirectTo: 'home', pathMatch: 'full' },
 { path: 'product/:id', component: ProductComponent},
 { path: 'login', component: LoginComponent},
 { path: 'profile', component: ProfileComponent},
 { path: 'ticket/:id', component: PaymentComponent},
 { path: 'signup', component: SignupComponent}
]

export const routing = RouterModule.forRoot(appRoutes)
