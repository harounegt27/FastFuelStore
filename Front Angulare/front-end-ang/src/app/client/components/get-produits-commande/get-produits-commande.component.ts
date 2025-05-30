import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-get-produits-commande',
  templateUrl: './get-produits-commande.component.html',
  styleUrls: ['./get-produits-commande.component.css']
})
export class GetProduitsCommandeComponent {

  commandeId: any = this.activatedRoute.snapshot.params['commandeId'];
  produitCommandeList = [];
  montant:any;


  constructor(
    private activatedRoute: ActivatedRoute,
    private clientService: ClientService
  ){}


  ngOnInit(){
    this.getProduitCommander();
  }

  getProduitCommander(): void {
    this.clientService.getCommandeProduits(this.commandeId).subscribe({
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
