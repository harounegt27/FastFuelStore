import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClientService } from '../../services/client.service';
import { DialogService } from 'src/app/services/Dialog/dialog.service';

@Component({
  selector: 'app-get-wishlist',
  templateUrl: './get-wishlist.component.html',
  styleUrls: ['./get-wishlist.component.css']
})
export class GetWishlistComponent {
  wishlist : any[] = [];

  constructor(
    private clientServie : ClientService,
    private snacks : MatSnackBar,
    private dialog : DialogService
  ){}


  ngOnInit(){
    this.getWishlist();
  }


  getWishlist(){
    this.wishlist = []
    this.clientServie.getWishlistByUser().subscribe(
      res => {
        res.forEach(element => {
          this.wishlist.push(element);
        })
      },
      (error) => {
        console.error('Une erreur s\'est produite lors du chargement de list des favoris : ', error);
        this.snacks.open('Une erreur s\'est produite lors du chargement  de list des favoris ', 'Fermer', {
          duration: 3000 // durée d'affichage du message en ms
        });
      }
    );
  }


  deleteProduct(id : any){
    this.dialog.confirm('Êtes-vous sûr de vouloir supprimer ce produit de la liste des favoris ?')
      .subscribe(confirmed => {
        if (confirmed) {
          this.clientServie.deleteProd(id).subscribe(res =>{
            if (res == null) {
              this.snacks.open('Produit a été supprimé avec succès !', 'Fermer', { duration: 5000 });
              this.getWishlist();
            }else{
              this.snacks.open(res.message, 'Fermer', { duration: 5000 , panelClass:'error-snackbar' });
            }
          });

        } else {
          this.snacks.open('Suppression annulée.', 'Fermer', { duration: 5000 });
        }
      });
  }
}
