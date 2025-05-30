import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-get-commande',
  templateUrl: './get-commande.component.html',
  styleUrls: ['./get-commande.component.css']
})
export class GetCommandeComponent {
  commandes : any[] = [];

  constructor(
    private clientServie : ClientService,
    private snacks : MatSnackBar
  ){}

ngOnInit(){
  this.getAllCommandes();
}


getAllCommandes(){
  this.commandes = []
  this.clientServie.getCommandeByUser().subscribe(
    res => {
      res.forEach(element => {
        this.commandes.push(element);
      })
    },
    (error) => {
      console.error('Une erreur s\'est produite lors du chargement des commandes : ', error);
      this.snacks.open('Une erreur s\'est produite lors du chargement des commandes', 'Fermer', {
        duration: 3000 // durée d'affichage du message en ms
      });
    }
  );
}



}
