import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PostCategorieComponent } from './components/post-categorie/post-categorie.component';
import { PostPrixUnitaireComponent } from './components/post-prix-unitaire/post-prix-unitaire.component';
import { PostProduitComponent } from './components/post-produit/post-produit.component';
import { PostStockComponent } from './components/post-stock/post-stock.component';
import { GetCategorieComponent } from './components/get-categorie/get-categorie.component';
import { UpdateCategorieComponent } from './components/update-categorie/update-categorie.component';
import { UpdateProduitComponent } from './components/update-produit/update-produit.component';
import { GetPrixUnitaireComponent } from './components/get-prix-unitaire/get-prix-unitaire.component';
import { UpdatePrixUnitaireComponent } from './components/update-prix-unitaire/update-prix-unitaire.component';
import { GetStockComponent } from './components/get-stock/get-stock.component';
import { UpdateStockComponent } from './components/update-stock/update-stock.component';
import { GetUsersComponent } from './components/get-users/get-users.component';
import { GetCommandesComponent } from './components/get-commandes/get-commandes.component';
import { FactureComponent } from './components/facture/facture.component';
import { InfoCommandeComponent } from './components/info-commande/info-commande.component';
import { AnalysesComponent } from './components/analyses/analyses.component';

const routes: Routes = [
   {path: '', component: AdminComponent},
   {path: 'dashboard', component: DashboardComponent},
   {path: 'ajouter-categorie', component: PostCategorieComponent},
   {path: 'ajouter-prixunitaire', component: PostPrixUnitaireComponent},
   {path: 'ajouter-produit', component: PostProduitComponent},
   {path: 'ajouter-stock', component: PostStockComponent},
   {path: 'tout-categorie', component: GetCategorieComponent},
   {path: 'maj-categorie/:id', component: UpdateCategorieComponent},
   {path: 'maj-produit/:produitId', component: UpdateProduitComponent},
   {path: 'tout-prix', component: GetPrixUnitaireComponent},
   {path: 'maj-prix/:prixId', component: UpdatePrixUnitaireComponent},
   {path: 'tout-stocks', component: GetStockComponent},
   {path: 'maj-stock/:stockId', component: UpdateStockComponent},
   {path: 'tout-users', component: GetUsersComponent},
   {path: 'tout-commandes', component: GetCommandesComponent},
   {path: 'facture/:commandeId', component: FactureComponent},
   {path: 'info-commande/:commandeId', component: InfoCommandeComponent },
   {path: 'analyses', component: AnalysesComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
