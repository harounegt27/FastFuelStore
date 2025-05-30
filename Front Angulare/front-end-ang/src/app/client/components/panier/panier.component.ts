import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogService } from 'src/app/services/Dialog/dialog.service';
import { MatDialog } from '@angular/material/dialog';
import { PostCommandeComponent } from '../post-commande/post-commande.component';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent {
  cartItems: any[] = [];
  commande:any;

  constructor(
    private clientService: ClientService,
    private fb : FormBuilder,
    private snacks : MatSnackBar,
    private dialog : DialogService,
    private matdialog : MatDialog
  ) {}

  ngOnInit(): void{
    this.getCart();
  }

  getCart(){
    this.cartItems = [];
    this.clientService.getCartByUser().subscribe(res =>{
      this.commande=res;
      res.cartItems.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64, ' + element.returnedImg;
        this.cartItems.push(element);
      });
    })
  }

  updateQuantity(produitId: any, event: any) {
    const newQte = event.target.value;
    this.clientService.updateQuantity(produitId, newQte).subscribe(res => {
      this.snacks.open('Quantité modifier !','Fermer',{duration : 5000})
      this.getCart();
    });
  }

  passerCommande(){
    this.matdialog.open(PostCommandeComponent);
  }

  Supprimer(produitId: any): void {
    // Appel du service pour supprimer l'élément du panier
    this.clientService.deleteItemFromCart(produitId).subscribe(
      response => {
        // Afficher un snack-bar pour indiquer que l'élément a été supprimé avec succès
        this.snacks.open('L\'élément du panier a été supprimé avec succès', 'Fermer', {
          duration: 3000
        });
      },
      error => {
        // Afficher un snack-bar pour indiquer qu'une erreur s'est produite lors de la suppression
        this.snacks.open('Une erreur s\'est produite lors de la suppression de l\'élément du panier', 'Fermer', {
          duration: 3000
        });
      }
    );
    this.getCart();
  }
}
