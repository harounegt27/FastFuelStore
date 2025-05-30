import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogService } from 'src/app/services/Dialog/dialog.service';
import { AdminService } from '../../service/admin.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-get-prix-unitaire',
  templateUrl: './get-prix-unitaire.component.html',
  styleUrls: ['./get-prix-unitaire.component.css']
})
export class GetPrixUnitaireComponent {

  prixunitaires : any[] = [];

  constructor(
    private adminService: AdminService,
    private snacks : MatSnackBar,
    private dialog : DialogService
  ) {}

  ngOnInit(): void {
    this.getAllPrix();
  }


  getAllPrix(): void {
    this.prixunitaires = [];
    this.adminService.allPrixUnitaire().subscribe(
      rep => {
        rep.forEach(element => {
          // Pour chaque élément, récupérez les détails du produit correspondant par son ID
          this.adminService.getPoduitById(element.produitId).subscribe(
            produit => {
              // Ajoutez les détails du produit à l'élément de prix unitaire
              element.produit = produit;
              this.prixunitaires.push(element);
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


  deletePrix(prixId: any) {
    this.dialog.confirm('Êtes-vous sûr de vouloir supprimer ce prix ?')
      .subscribe(confirmed => {
        if (confirmed) {
          this.adminService.deletePrix(prixId).subscribe(res =>{
            if (res == null) {
              this.snacks.open('Prix Unitaire a été supprimé avec succès !', 'Fermer', { duration: 5000 });
              this.getAllPrix();
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



