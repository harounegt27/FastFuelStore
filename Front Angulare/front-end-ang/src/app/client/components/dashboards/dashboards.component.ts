import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ClientService } from '../../services/client.service';
import { switchMap } from 'rxjs/internal/operators/switchMap';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserStorageService } from 'src/app/services/stockage/user-storage.service';

@Component({
  selector: 'app-dashboards',
  templateUrl: './dashboards.component.html',
  styleUrls: ['./dashboards.component.css']
})
export class DashboardsComponent {
  products: any[] = [];
  prixUnitairesMap: Map<number, any[]> = new Map();
  searchProductForm !: FormGroup;
  selectedVoixId: number | null = null;


  constructor(
    private clientService: ClientService,
    private fb: FormBuilder,
    private snacks : MatSnackBar
    ) {}

  ngOnInit(): void {
    this.getAllProduits();
    this.searchProductForm = this.fb.group({
      title:[null, [Validators.required]]
    })
  }

  getAllProduits(): void {
    this.products = [];
    this.clientService.allProduit().pipe(
      switchMap((res: any[]) => {
        this.products = res.map(element => {
          element.processedImg = 'data:image/jpeg;base64, ' + element.byteImg;
          return element;
        });
        return forkJoin(this.products.map(product => this.clientService.getPrixUnitairesByProduitId(product.id)));
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
    this.clientService.allProduitByName(title).pipe(
      switchMap((res: any[]) => {
        this.products = res.map(element => {
          element.processedImg = 'data:image/jpeg;base64, ' + element.byteImg;
          return element;
        });
        return forkJoin(this.products.map(product => this.clientService.getPrixUnitairesByProduitId(product.id)));
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

  addToCart(productId: any) {
    if (this.selectedVoixId !== null) {
      this.clientService.AddProduitToCart(productId, this.selectedVoixId).subscribe(res =>{
        this.snacks.open('Produit ajouté au panier avec succès !','Fermer',{duration : 5000});
      });
    } else {
      this.snacks.open('Echèc ajout produit au panier','ERROR',{duration:5000});
    }
  }

  selectVoix(voiceId: number) {
    this.selectedVoixId = voiceId;
  }

  addToFavorites(productId: any) {
    this.clientService.addProduitToWishlist(productId).subscribe(
      (response) => {
        this.snacks.open('Produit ajouté au liste favoris avec succès !','Fermer',{duration : 5000});
      },
      (error) => {
        this.snacks.open('Echèc ajout produit au list favoris','ERROR',{duration:5000});
      }
    );
  }

}


