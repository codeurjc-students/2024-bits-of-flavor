import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';
import { ProductComponent } from './components/product/product.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { PaymentComponent } from './components/payment/payment.component';
import { SignupComponent } from './components/signup/signup.component';
import { NewProduct } from './components/admin/newProduct.component';
import { OfferManagement } from './components/admin/offerManagement.component';
import { StatisticsComponent } from './components/graphic/statistics.component';
import { ErrorComponent } from './components/error/error.component';

const appRoutes: Routes = [
 { path: 'home', component: HomeComponent},
 { path: 'search', component: SearchComponent},
 { path: '', redirectTo: 'home', pathMatch: 'full' },
 { path: 'product/:id', component: ProductComponent},
 { path: 'login', component: LoginComponent},
 { path: 'profile', component: ProfileComponent},
 { path: 'ticket/:id', component: PaymentComponent},
 { path: 'signup', component: SignupComponent},
 { path: 'newProduct', component: NewProduct},
 { path: 'offerManagement', component: OfferManagement},
 { path: 'statistics', component: StatisticsComponent},
 { path: 'error', component: ErrorComponent},
 { path: '**', redirectTo: 'error', pathMatch: 'full'}
]

export const routing = RouterModule.forRoot(appRoutes)
