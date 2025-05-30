import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogService } from 'src/app/services/Dialog/dialog.service';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-get-stock',
  templateUrl: './get-stock.component.html',
  styleUrls: ['./get-stock.component.css']
})
export class GetStockComponent {
  stocks : any[] = [];

  constructor(
    private adminService: AdminService,
    private snacks : MatSnackBar,
    private dialog : DialogService
  ) {}

  ngOnInit(): void {
    this.getAllStock();
  }

  getAllStock():void{
    this.stocks = [];
    this.adminService.allStock().subscribe(
      rep => {
        rep.forEach(element => {
          // Pour chaque élément, récupérez les détails du produit correspondant par son ID
          this.adminService.getPoduitById(element.produitId).subscribe(
            produit => {
              // Ajoutez les détails du produit à l'élément de prix unitaire
              element.produit = produit;
              this.stocks.push(element);
            },
            error => {
              console.error('Erreur lors de la récupération des détails du produit : ', error);
            }
          );
        });
      },
      error => {
        console.error('Erreur lors de la récupération des prix unitaires : ', error);
      }
    );
  }

  deleteStock(stockId: any) {
    this.dialog.confirm('Êtes-vous sûr de vouloir supprimer ce stock ?')
      .subscribe(confirmed => {
        if (confirmed) {
          this.adminService.deleteStock(stockId).subscribe(res =>{
            if (res == null) {
              this.snacks.open('Stock a été supprimé avec succès !', 'Fermer', { duration: 5000 });
              this.getAllStock();
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
