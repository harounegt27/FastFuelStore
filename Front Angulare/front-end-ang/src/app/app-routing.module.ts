import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { MainHomeComponent } from './home/mainHome/main-home/main-home.component';
import { ProduitsComponent } from './produits/produits.component';

const routes: Routes = [
  { path: 'signup', component: SignupComponent  },
  { path: 'login', component: LoginComponent },
  { path: 'qui-sommes-nous', component: MainHomeComponent },
  { path: 'produits', component: ProduitsComponent },
  { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
  { path: 'client', loadChildren: () => import('./client/client.module').then(m => m.ClientModule) }, // Route pour la page de connexion
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
