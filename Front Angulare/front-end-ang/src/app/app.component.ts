import { Component, OnInit } from '@angular/core';
import { UserStorageService } from './services/stockage/user-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  isClientLoggedIn : boolean = UserStorageService.isClientLoggedIn();
  isAdminLoggedIn : boolean = UserStorageService.isAdminLoggedIn();

  constructor(private router : Router){};

  ngOnInit(): void {
    const background = document.querySelector('.background') as HTMLElement;
    let luminosity = 0;
    let increasing = true;

    function animateBackground() {
      // Augmenter ou diminuer la luminosité en fonction de la direction
      luminosity = increasing ? luminosity + 0.1 : luminosity - 0.1;

      // Mettre à jour le dégradé de couleur en fonction de la luminosité
      const color = `linear-gradient(45deg, hsl(200, 100%, ${luminosity}%), hsl(200, 100%, ${luminosity + 20}%), hsl(200, 100%, ${luminosity + 40}%))`;
      background.style.background = color;

      // Inverser la direction lorsque la luminosité atteint 0 ou 60
      if (luminosity >= 40 || luminosity <= 0) {
        increasing = !increasing;
      }

      // Continuer l'animation en boucle
      requestAnimationFrame(animateBackground);
    }
    animateBackground();

    this.router.events.subscribe(event =>{
      this.isClientLoggedIn = UserStorageService.isClientLoggedIn();
      this.isAdminLoggedIn = UserStorageService.isAdminLoggedIn();
    });
  }

  logout(){
    UserStorageService.signout();
    this.router.navigateByUrl('');
  }






  }
