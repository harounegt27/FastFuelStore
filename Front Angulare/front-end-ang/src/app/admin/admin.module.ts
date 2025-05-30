import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PostCategorieComponent } from './components/post-categorie/post-categorie.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { HttpClientModule } from '@angular/common/http';
import { PostPrixUnitaireComponent } from './components/post-prix-unitaire/post-prix-unitaire.component';
import { PostProduitComponent } from './components/post-produit/post-produit.component';
import { MatIconModule } from '@angular/material/icon';
import { PostStockComponent } from './components/post-stock/post-stock.component';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { GetCategorieComponent } from './components/get-categorie/get-categorie.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { UpdateCategorieComponent } from './components/update-categorie/update-categorie.component';
import { UpdateProduitComponent } from './components/update-produit/update-produit.component';
import { GetPrixUnitaireComponent } from './components/get-prix-unitaire/get-prix-unitaire.component';
import { UpdatePrixUnitaireComponent } from './components/update-prix-unitaire/update-prix-unitaire.component';
import { GetStockComponent } from './components/get-stock/get-stock.component';
import { UpdateStockComponent } from './components/update-stock/update-stock.component';
import { GetUsersComponent } from './components/get-users/get-users.component';
import { GetCommandesComponent } from './components/get-commandes/get-commandes.component';
import { MatTableModule } from '@angular/material/table';
import { MatMenuModule } from '@angular/material/menu';
import { FactureComponent } from './components/facture/facture.component';
import { InfoCommandeComponent } from './components/info-commande/info-commande.component';
import { AnalysesComponent } from './components/analyses/analyses.component';


@NgModule({
  declarations: [
    AdminComponent,
    DashboardComponent,
    PostCategorieComponent,
    PostPrixUnitaireComponent,
    PostProduitComponent,
    PostStockComponent,
    GetCategorieComponent,
    UpdateCategorieComponent,
    UpdateProduitComponent,
    GetPrixUnitaireComponent,
    UpdatePrixUnitaireComponent,
    GetStockComponent,
    UpdateStockComponent,
    GetUsersComponent,
    GetCommandesComponent,
    FactureComponent,
    InfoCommandeComponent,
    AnalysesComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatSnackBarModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatIconModule,
    HttpClientModule,
    MatCardModule,
    MatDividerModule,
    MatDialogModule,
    MatButtonModule,
    MatTableModule,
    MatMenuModule
  ]
})
export class AdminModule { }
