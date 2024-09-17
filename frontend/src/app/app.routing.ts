import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';

const appRoutes: Routes = [
 { path: 'home', component: HomeComponent},
 { path: 'search', component: SearchComponent},
 { path: '', redirectTo: 'home', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes)
