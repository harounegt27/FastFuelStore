import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogService } from 'src/app/services/Dialog/dialog.service';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-get-users',
  templateUrl: './get-users.component.html',
  styleUrls: ['./get-users.component.css']
})
export class GetUsersComponent {
  users: any[] = [];

  constructor(
    private adminService: AdminService,
    private snacks : MatSnackBar,
    private dialog : DialogService
  ) {}


  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers(){
    this.users = [];
    this.adminService.allUsers().subscribe( cat =>{
      cat.forEach(element =>{
        this.users.push(element);
      })
    });
  }

  deleteUser(userId: any) {
    this.dialog.confirm('Êtes-vous sûr de vouloir supprimer ce client ?')
      .subscribe(confirmed => {
        if (confirmed) {
          const messageBody = 'Votre compte a été supprimé.'; // Définir le corps du message

          // Appeler la méthode pour récupérer l'e-mail de l'utilisateur par son ID
          this.adminService.getUserById(userId).subscribe(
            (user) => {
              const userEmail = user.email; // Récupérer l'e-mail de l'utilisateur
              // Envoyer l'e-mail avec l'e-mail récupéré et le corps du message
              this.adminService.sendEmail(userEmail, 'Suppression de compte', messageBody).subscribe(
                () => {
                  this.snacks.open('Email envoier avec succès !', 'Fermer', { duration: 5000 });
                },
                (error) => {
                  this.snacks.open('Message echoué avec succès !', 'Fermer', { duration: 5000 , panelClass: 'error-snackbar' });
                }
              );
            },
            (error) => {
              // Gérer les erreurs de récupération de l'utilisateur si nécessaire
            }
          );

          // Supprimer l'utilisateur après l'envoi de l'e-mail
          this.adminService.deleteUser(userId).subscribe(
            () => {
              this.snacks.open('Client supprimé avec succès !', 'Fermer', { duration: 5000 });
              this.getAllUsers();
            },
            (error) => {
              this.snacks.open('Une erreur s\'est produite lors de la suppression de la client.', 'Fermer', { duration: 5000, panelClass: 'error-snackbar' });
            }
          );
        } else {
          this.snacks.open('Suppression annulée.', 'Fermer', { duration: 5000 });
        }
      });
  }
}
