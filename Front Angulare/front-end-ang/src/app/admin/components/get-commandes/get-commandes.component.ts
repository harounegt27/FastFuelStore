import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogService } from 'src/app/services/Dialog/dialog.service';
import { FactureComponent } from '../facture/facture.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-get-commandes',
  templateUrl: './get-commandes.component.html',
  styleUrls: ['./get-commandes.component.css']
})
export class GetCommandesComponent {

  commandes : any[] = [];

  constructor(
    private adminService : AdminService,
    private snacks : MatSnackBar,
    private dialog : DialogService,
    private matdialogue : MatDialog
  ){}

ngOnInit(){
  this.getAllCommandes();
}


getAllCommandes(){
  this.commandes = []
  this.adminService.allCommandes().subscribe(
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

changeCommandeStatue(commandeId: any, statue: string) {
  this.adminService.changeCommandeStatue(commandeId, statue).subscribe(
    res => {
      if (res.id != null) {
        this.snacks.open('État de la commande modifié avec succès !', 'Fermer', { duration: 5000 });

        // Définir le corps du message
        const messageBody = `L'état de votre commande a été changé à : ${statue}.`;

        // Appeler la méthode pour récupérer l'e-mail de l'utilisateur par son ID
        this.adminService.getUserById(res.userId).subscribe(
          user => {
            const userEmail = user.email; // Récupérer l'e-mail de l'utilisateur
            // Envoyer l'e-mail avec l'e-mail récupéré et le corps du message
            this.adminService.sendEmail(userEmail, 'Mise à jour de l\'état de la commande', messageBody).subscribe(
              () => {
                this.snacks.open('Email envoyé avec succès !', 'Fermer', { duration: 5000 });
              },
              error => {
                this.snacks.open('Échec de l\'envoi de l\'email.', 'Fermer', { duration: 5000, panelClass: 'error-snackbar' });
              }
            );
          },
          error => {
            // Gérer les erreurs de récupération de l'utilisateur si nécessaire
            this.snacks.open('Erreur lors de la récupération de l\'utilisateur.', 'Fermer', { duration: 5000, panelClass: 'error-snackbar' });
          }
        );

        this.getAllCommandes();
      } else {
        this.snacks.open('Erreur lors de la modification de l\'état de la commande.', 'Fermer', { duration: 5000, panelClass: 'error-snackbar' });
      }
    },
    error => {
      this.snacks.open('Erreur lors de la modification de l\'état de la commande.', 'Fermer', { duration: 5000, panelClass: 'error-snackbar' });
    }
  );
}


refuserCommande(commandeId:any){
  this.dialog.confirm('Êtes-vous sûr de réfuser cette commande ?')
      .subscribe(confirmed => {
        if (confirmed) {
          this.adminService.deleteCommande(commandeId).subscribe(
            (res) => {
              this.snacks.open('Commande supprimée avec succès !', 'Fermer', { duration: 5000 });
              this.getAllCommandes();
            },
            (error) => {
              this.snacks.open('Une erreur s\'est produite lors de la suppression de la commande.', 'Fermer', { duration: 5000, panelClass: 'error-snackbar' });
            }
          );

        } else {
          this.snacks.open('Suppression annulée.', 'Fermer', { duration: 5000 });
        }
      });
}

openFactureDialog(commande: any) {
  this.matdialogue.open(FactureComponent, {
    data: { commande: commande },
    width: '80%', // Optionnel : ajustez la largeur de la boîte de dialogue
  });
}

}
