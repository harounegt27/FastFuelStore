import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { forkJoin } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogService } from 'src/app/services/Dialog/dialog.service';



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  products: any[] = [];
  prixUnitairesMap: Map<number, any[]> = new Map();
  searchProductForm !: FormGroup;


  constructor(
    private adminService: AdminService,
    private fb: FormBuilder,
    private snacks : MatSnackBar,
    private dialog : DialogService
      ) {}

  ngOnInit(): void {
    this.getAllProduits();
    this.searchProductForm = this.fb.group({
      title:[null, [Validators.required]]
    })
  }

  getAllProduits(): void {
    this.products = [];
    this.adminService.allProduit().pipe(
      switchMap((res: any[]) => {
        this.products = res.map(element => {
          element.processedImg = 'data:image/jpeg;base64, ' + element.byteImg;
          return element;
        });
        return forkJoin(this.products.map(product => this.adminService.getPrixUnitairesByProduitId(product.id)));
      })
    ).subscribe(
      (prixUnitairesArrays: any[][]) => {
        prixUnitairesArrays.forEach((prixUnitaires, index) => {
          this.prixUnitairesMap.set(this.products[index].id, prixUnitaires);
        });
      },
      error => {
        console.error('Error loading prix unitaires:', error);
      }
    );
  }

  submitForm(){
    this.products = [];
    const title = this.searchProductForm.get('title')!.value;
    this.adminService.allProduitByName(title).pipe(
      switchMap((res: any[]) => {
        this.products = res.map(element => {
          element.processedImg = 'data:image/jpeg;base64, ' + element.byteImg;
          return element;
        });
        return forkJoin(this.products.map(product => this.adminService.getPrixUnitairesByProduitId(product.id)));
      })
    ).subscribe(
      (prixUnitairesArrays: any[][]) => {
        prixUnitairesArrays.forEach((prixUnitaires, index) => {
          this.prixUnitairesMap.set(this.products[index].id, prixUnitaires);
        });
      },
      error => {
        console.error('Error loading prix unitaires:', error);
      }
    );
  }


  deleteProduit(produitId: any) {
    this.dialog.confirm('Êtes-vous sûr de vouloir supprimer ce produit ?')
      .subscribe(confirmed => {
        if (confirmed) {
          this.adminService.deletePoduit(produitId).subscribe(res =>{
            if (res == null) {
              this.snacks.open('Produit a été supprimé avec succès !', 'Fermer', { duration: 5000 });
              this.getAllProduits();
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
