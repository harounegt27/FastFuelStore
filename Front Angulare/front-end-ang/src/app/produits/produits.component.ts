import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { switchMap, forkJoin } from 'rxjs';
import { InternauteServiceService } from '../services/internaute-service.service';

@Component({
  selector: 'app-produits',
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.css']
})
export class ProduitsComponent {
  products: any[] = [];
  prixUnitairesMap: Map<number, any[]> = new Map();
  searchProductForm !: FormGroup;


  constructor(
    private clientService: InternauteServiceService,
    private fb: FormBuilder,

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


}
