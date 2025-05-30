import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from './client.component';
import { DashboardsComponent } from './components/dashboards/dashboards.component';
import { PanierComponent } from './components/panier/panier.component';
import { PostCommandeComponent } from './components/post-commande/post-commande.component';
import { GetCommandeComponent } from './components/get-commande/get-commande.component';
import { GetWishlistComponent } from './components/get-wishlist/get-wishlist.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { GetProduitsCommandeComponent } from './components/get-produits-commande/get-produits-commande.component';
import { ReviwProduitCommanderComponent } from './components/reviw-produit-commander/reviw-produit-commander.component';

const routes: Routes = [
  { path: '', component: ClientComponent },
  { path: 'dashboards', component: DashboardsComponent },
  { path: 'panier', component: PanierComponent },
  { path: 'passer-commande', component: PostCommandeComponent },
  { path: 'tout-commandes', component: GetCommandeComponent },
  { path: 'wishlist/:userId', component: GetWishlistComponent },
  { path: 'maj-user/:userId', component: UpdateUserComponent },
  { path: 'produits-commande/:commandeId', component: GetProduitsCommandeComponent },
  { path: 'review/:produitId', component: ReviwProduitCommanderComponent },



];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
