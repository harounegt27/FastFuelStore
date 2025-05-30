import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-info-commande',
  templateUrl: './info-commande.component.html',
  styleUrls: ['./info-commande.component.css']
})
export class InfoCommandeComponent {

  commandeId: any = this.activatedRoute.snapshot.params['commandeId'];
  produitCommandeList = [];
  montant:any;


  constructor(
    private activatedRoute: ActivatedRoute,
    private adminService: AdminService
  ){}


  ngOnInit(){
    this.getProduitCommander();
  }

  getProduitCommander(): void {
    this.adminService.getCommandeProduits(this.commandeId).subscribe({
      next: (res) => {
        res.produitDtosList.forEach(element => {
          element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
          this.produitCommandeList.push(element);
        });
        this.montant = res.montant_totale;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des produits commandés : ', err);
      }
    });
  }

}
