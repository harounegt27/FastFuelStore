import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserStorageService } from '../services/stockage/user-storage.service';
import { ClientService } from '../client/services/client.service';

@Component({
  selector: 'app-navbarclient',
  templateUrl: './navbarclient.component.html',
  styleUrls: ['./navbarclient.component.css']
})
export class NavbarclientComponent {

  nomClient: string = '';
  userId:any = UserStorageService.getUserId();

  constructor(
    private router: Router,
    private clientService : ClientService
  ) {}

  ngOnInit(): void {
    this.getUserName(); // Appelez la méthode pour récupérer le nom du client au chargement du composant
  }

  logout(): void {
    UserStorageService.signout();
    this.router.navigateByUrl('');
  }

  getUserName(): void {
    this.clientService.getUserName(this.userId).subscribe(
      (response) => {
        this.nomClient = response.name; // Mettez à jour le nom du client avec la réponse du service
      },
      (error) => {
        console.error('Erreur lors de la récupération du nom du client : ', error);
      }
    );
  }
}
