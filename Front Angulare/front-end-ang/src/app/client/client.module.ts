import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { ClientComponent } from './client.component';
import { DashboardsComponent } from './components/dashboards/dashboards.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { PanierComponent } from './components/panier/panier.component';
import { PostCommandeComponent } from './components/post-commande/post-commande.component';
import { MatSelectModule } from '@angular/material/select';
import { GetCommandeComponent } from './components/get-commande/get-commande.component';
import { GetWishlistComponent } from './components/get-wishlist/get-wishlist.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { GetProduitsCommandeComponent } from './components/get-produits-commande/get-produits-commande.component';
import { ReviwProduitCommanderComponent } from './components/reviw-produit-commander/reviw-produit-commander.component';


@NgModule({
  declarations: [
    ClientComponent,
    DashboardsComponent,
    PanierComponent,
    PostCommandeComponent,
    GetCommandeComponent,
    GetWishlistComponent,
    UpdateUserComponent,
    GetProduitsCommandeComponent,
    ReviwProduitCommanderComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatCardModule,
    MatDividerModule,
    MatDialogModule,
    MatButtonModule,
    MatSelectModule
  ]
})
export class ClientModule { }
