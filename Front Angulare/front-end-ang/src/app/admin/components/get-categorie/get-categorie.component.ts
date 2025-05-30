import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from 'src/app/confirmation-dialog-component/confirmation-dialog-component.component';
import { DialogService } from 'src/app/services/Dialog/dialog.service';

@Component({
  selector: 'app-get-categorie',
  templateUrl: './get-categorie.component.html',
  styleUrls: ['./get-categorie.component.css']
})
export class GetCategorieComponent {
  categories: any[] = [];

  constructor(
    private adminService: AdminService,
    private snacks : MatSnackBar,
    private dialog : DialogService
  ) {}


  ngOnInit(): void {
    this.getAllCategories();
  }

  getAllCategories(){
    this.categories = [];
    this.adminService.allCategorie().subscribe( cat =>{
      cat.forEach(element =>{
        this.categories.push(element);
      })
    });
  }

  deleteCategorie(categorieId: any) {
    this.dialog.confirm('Êtes-vous sûr de vouloir supprimer cette catégorie ?')
      .subscribe(confirmed => {
        if (confirmed) {
          this.adminService.deleteCategorie(categorieId).subscribe(
            (res) => {
              this.snacks.open('Catégorie supprimée avec succès !', 'Fermer', { duration: 5000 });
              this.getAllCategories();
            },
            (error) => {
              this.snacks.open('Une erreur s\'est produite lors de la suppression de la catégorie.', 'Fermer', { duration: 5000, panelClass: 'error-snackbar' });
            }
          );

        } else {
          this.snacks.open('Suppression annulée.', 'Fermer', { duration: 5000 });
        }
      });
  }


}
